package com.njust.springboot.controller;

import com.njust.springboot.common.Result;
import com.njust.springboot.entity.Lecture;
import com.njust.springboot.entity.Quiz;
import com.njust.springboot.entity.QuizOption;
import com.njust.springboot.service.LectureService;
import com.njust.springboot.service.QuizService;
import com.njust.springboot.service.QuizOptionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njust.springboot.entity.Answer;
import com.njust.springboot.service.AnswerService;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    @Resource
    private LectureService lectureService;
    @Resource
    private QuizService quizService;
    @Resource
    private QuizOptionService quizOptionService;
    @Resource
    private AnswerService answerService;

    // AI API Key
    private static final String AI_API_KEY = "ae490b003f8045d2b316cebf3473556d.kzMd7lVryNptR9Zl";
    private static final String AI_API_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";

    @GetMapping
    public Result getAll() {
        return Result.success(lectureService.getAllLectures());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.success(lectureService.getLectureById(id));
    }

    @GetMapping("/stats/{id}")
    public Result getLectureStats(@PathVariable Long id) {
        // 统计参与人数、答题总数、平均正确率
        Map<String, Object> stats = lectureService.getLectureStats(id);
        return Result.success(stats);
    }

    @PostMapping
    public Result add(@RequestBody Lecture lecture) {
        lectureService.addLecture(lecture);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Lecture lecture) {
        lectureService.updateLecture(lecture);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return Result.success();
    }

    @PostMapping("/upload")
    public Result uploadLecture(
            @RequestParam("fileUrl") String fileUrl,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("speakerId") Long speakerId,
            @RequestParam("organizerId") Long organizerId
    ) {
        Lecture lecture = new Lecture();
        lecture.setTitle(title);
        lecture.setDescription(description);
        lecture.setSpeakerId(speakerId);
        lecture.setOrganizerId(organizerId);
        lecture.setFileUrl(fileUrl);
        lectureService.addLecture(lecture);
        System.out.println("准备调用AI出题，lectureId=" + lecture.getId() + ", fileUrl=" + fileUrl);
        // 自动AI出题
        try {
            String content = downloadFileContent(fileUrl);
            System.out.println("文件内容长度：" + (content != null ? content.length() : 0));
            generateAndSaveQuizForLecture(lecture, content);
        } catch (Exception e) {
            System.err.println("AI出题前异常：" + e.getMessage());
            e.printStackTrace();
        }
        return Result.success(lecture.getId());
    }

    // 下载fileUrl内容为字符串
    private String downloadFileContent(String fileUrl) throws Exception {
        int idx = fileUrl.lastIndexOf('/');
        String prefix = fileUrl.substring(0, idx + 1);
        String fileName = fileUrl.substring(idx + 1);
        String encodedFileName = java.net.URLEncoder.encode(fileName, java.nio.charset.StandardCharsets.UTF_8.name());
        String encodedUrl = prefix + encodedFileName;
        java.net.URL url = new java.net.URL(encodedUrl);

        // 保存到临时文件
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        java.io.File tempFile = java.io.File.createTempFile("lecture_", ext);
        try (java.io.InputStream in = url.openStream();
             java.io.FileOutputStream out = new java.io.FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        String text = "";
        if (fileName.toLowerCase().endsWith(".pdf")) {
            // PDF
            try (org.apache.pdfbox.pdmodel.PDDocument document = org.apache.pdfbox.pdmodel.PDDocument.load(tempFile)) {
                org.apache.pdfbox.text.PDFTextStripper stripper = new org.apache.pdfbox.text.PDFTextStripper();
                text = stripper.getText(document);
            }
        } else if (fileName.toLowerCase().endsWith(".pptx")) {
            // PPTX
            try (org.apache.poi.xslf.usermodel.XMLSlideShow ppt = new org.apache.poi.xslf.usermodel.XMLSlideShow(new java.io.FileInputStream(tempFile))) {
                StringBuilder sb = new StringBuilder();
                for (org.apache.poi.xslf.usermodel.XSLFSlide slide : ppt.getSlides()) {
                    for (org.apache.poi.xslf.usermodel.XSLFShape shape : slide.getShapes()) {
                        if (shape instanceof org.apache.poi.xslf.usermodel.XSLFTextShape) {
                            sb.append(((org.apache.poi.xslf.usermodel.XSLFTextShape) shape).getText()).append("\n");
                        }
                    }
                }
                text = sb.toString();
            }
        } else {
            text = "暂不支持该文件类型";
        }
        tempFile.delete();
        return text;
    }

    // AI出题并保存到数据库（自动解析AI返回并入库）
    private void generateAndSaveQuizForLecture(Lecture lecture, String content) {
        try {
            System.out.println("进入AI出题方法，lectureId=" + lecture.getId());
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + AI_API_KEY);
            Map<String, Object> payload = new HashMap<>();
            payload.put("model", "glm-4");
            List<Map<String, String>> messages = new ArrayList<>();
            Map<String, String> message = new HashMap<>();
            message.put("role", "user");
            // 只取前3000字作为prompt，提升速度且防止AI跑题
            String promptContent = content.length() > 3000 ? content.substring(0, 3000) : content;
            String prompt = "请根据以下内容只生成5个单选选择题，每题4个选项，选项必须是A、B、C、D，答案用A/B/C/D表示，返回标准JSON数组，格式如下：\\n" +
                "[{\"question\":\"题干\",\"options\":[\"A. ...\",\"B. ...\",\"C. ...\",\"D. ...\"],\"answer\":\"A\"},...]\\n内容：" + promptContent;
            message.put("content", prompt);
            messages.add(message);
            payload.put("messages", messages);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(AI_API_URL, request, String.class);

            // 打印AI原始返回
            System.out.println("AI原始返回：" + response.getBody());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String aiContent = root.path("choices").get(0).path("message").path("content").asText();
            System.out.println("AI题目原始content：" + aiContent);
            // 用正则提取第一个 {...} 或 [...] 代码块
            String jsonBlock = null;
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("(?s)(\\{.*\\}|\\[.*\\])").matcher(aiContent);
            if (m.find()) {
                jsonBlock = m.group(1);
            }
            if (jsonBlock == null) {
                System.err.println("未提取到JSON代码块！");
                System.err.println("原始content：" + aiContent);
                return;
            }
            System.out.println("AI题目JSON代码块：" + jsonBlock);
            // 解析为JsonNode并兼容三种格式
            JsonNode quizzes = null;
            try {
                quizzes = mapper.readTree(jsonBlock);
                if (quizzes.isObject()) {
                    if (quizzes.has("quizzes")) {
                        quizzes = quizzes.get("quizzes");
                    } else if (quizzes.has("questions")) {
                        quizzes = quizzes.get("questions");
                    } else {
                        quizzes = null;
                    }
                }
            } catch (Exception ex) {
                System.err.println("AI题目JSON解析异常：" + ex.getMessage());
                System.err.println("jsonBlock内容：" + jsonBlock);
                return;
            }
            if (quizzes == null || !quizzes.isArray() || quizzes.size() == 0) {
                System.err.println("AI返回未解析出题目，请检查AI返回格式和内容！");
                System.err.println("最终jsonBlock内容：" + jsonBlock);
                return;
            }
            int fileIndex = 1;
            String[] labels = {"A", "B", "C", "D"};
            for (JsonNode quizNode : quizzes) {
                try {
                    JsonNode qNode = quizNode.get("question");
                    JsonNode aNode = quizNode.get("answer");
                    JsonNode oNode = quizNode.get("options");
                    if (qNode == null || aNode == null || oNode == null || !oNode.isArray() || oNode.size() != 4) {
                        System.err.println("题目缺少必要字段或选项数量不为4，跳过该题：" + quizNode.toString());
                        continue;
                    }
                    boolean valid = true;
                    for (int i = 0; i < 4; i++) {
                        String optText = oNode.get(i).asText();
                        if (!optText.startsWith(labels[i] + ".")) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) {
                        System.err.println("选项标签不规范，跳过该题：" + quizNode.toString());
                        continue;
                    }
                    String answer = aNode.asText();
                    if (!Arrays.asList(labels).contains(answer)) {
                        System.err.println("答案不规范，跳过该题：" + quizNode.toString());
                        continue;
                    }
                    String question = qNode.asText();
                    Quiz quiz = new Quiz();
                    quiz.setLectureId(lecture.getId());
                    quiz.setQuestionText(question);
                    quiz.setCorrectOption(answer);
                    quiz.setFileIndex(fileIndex++); // 题序递增
                    quizService.addQuiz(quiz);
                    // 强制刷新 quizId
                    Long quizId = quiz.getId();
                    if (quizId == null) {
                        List<Quiz> all = quizService.getAllQuizzes();
                        for (Quiz q : all) {
                            if (q.getLectureId().equals(lecture.getId()) && q.getQuestionText().equals(question)) {
                                quizId = q.getId();
                                break;
                            }
                        }
                    }
                    if (quizId == null) {
                        System.err.println("Quiz插入后quizId仍为null，无法插入选项！");
                        continue;
                    }
                    for (int i = 0; i < 4; i++) {
                        String optText = oNode.get(i).asText();
                        String label = labels[i];
                        String text = optText.length() > 2 ? optText.substring(2) : optText;
                        QuizOption option = new QuizOption();
                        option.setQuizId(quizId);
                        option.setOptionLabel(label);
                        option.setOptionText(text);
                        quizOptionService.addQuizOption(option);
                    }
                } catch (Exception ex) {
                    System.err.println("单题解析异常：" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("AI出题异常：" + e.getMessage());
        }
    }

    // 讲座详情接口，含题目和选项
    @GetMapping("/{id}/detail")
    public Result getLectureDetail(@PathVariable Long id) {
        Lecture lecture = lectureService.getLectureById(id);
        List<Quiz> quizzes = quizService.getAllQuizzes();
        List<Map<String, Object>> quizList = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            if (quiz.getLectureId().equals(id)) {
                List<QuizOption> options = quizOptionService.getAllQuizOptions();
                List<QuizOption> quizOptions = new ArrayList<>();
                for (QuizOption opt : options) {
                    // 防御性判断，quizId为null直接跳过
                    if (opt.getQuizId() == null) continue;
                    if (opt.getQuizId().equals(quiz.getId())) {
                        quizOptions.add(opt);
                    }
                }
                // 统计答题人数和正确率
                int answerCount = 0;
                int correctCount = 0;
                List<Answer> allAnswers = answerService.getAllAnswers();
                for (Answer ans : allAnswers) {
                    if (ans.getQuizId().equals(quiz.getId())) {
                        answerCount++;
                        if (Boolean.TRUE.equals(ans.getIsCorrect())) {
                            correctCount++;
                        }
                    }
                }
                double correctRate = answerCount > 0 ? (correctCount * 100.0 / answerCount) : 0.0;
                Map<String, Object> quizMap = new HashMap<>();
                quizMap.put("quiz", quiz);
                quizMap.put("options", quizOptions);
                quizMap.put("answerCount", answerCount);
                quizMap.put("correctRate", correctRate);
                quizList.add(quizMap);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("lecture", lecture);
        data.put("quizzes", quizList);
        return Result.success(data);
    }

    @GetMapping("/detail/{id}")
    public Result getLectureDetail(@PathVariable Long id, @RequestParam String role) {
        try {
            Lecture lecture = lectureService.getLectureById(id);
            List<Quiz> quizzes = quizService.getQuizzesByLectureId(id);
            List<Map<String, Object>> quizList = new ArrayList<>();
            for (Quiz quiz : quizzes) {
                Map<String, Object> quizMap = new HashMap<>();
                quizMap.put("id", quiz.getId());
                quizMap.put("question", quiz.getQuestionText());
                // 选项
                List<QuizOption> options = quizOptionService.getOptionsByQuizId(quiz.getId());
                List<Map<String, Object>> optList = new ArrayList<>();
                for (QuizOption opt : options) {
                    Map<String, Object> optMap = new HashMap<>();
                    optMap.put("optionLabel", opt.getOptionLabel());
                    optMap.put("optionText", opt.getOptionText());
                    optList.add(optMap);
                }
                quizMap.put("options", optList);
                // 正确答案
                if (!"audience".equals(role)) {
                    quizMap.put("answer", quiz.getCorrectOption());
                }
                // 单题统计
                Map<String, Object> stats = quizService.getQuizStats(quiz.getId());
                quizMap.put("stats", stats != null ? stats : new HashMap<>());
                quizList.add(quizMap);
            }
            Map<String, Object> result = new HashMap<>();
            result.put("lecture", lecture != null ? lecture : new HashMap<>());
            result.put("quizzes", quizList);
            // 讲座整体统计
            Map<String, Object> lectureStats = lectureService.getLectureStats(id);
            result.put("lectureStats", lectureStats != null ? lectureStats : new HashMap<>());
            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("lecture", new HashMap<>());
            result.put("quizzes", new ArrayList<>());
            result.put("lectureStats", new HashMap<>());
            return Result.success(result);
        }
    }
} 