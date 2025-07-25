<template>
  <div class="lecture-detail-container">
    <Header />
    <main>
      <h2 class="lecture-title">讲座名称：{{ lecture.title }}</h2>
      <div v-if="loading" class="loading">加载中...</div>
      <div v-if="error" class="error">{{ error }}</div>
      <div v-if="lecture">
        <div class="lecture-info-card">
          <div class="lecture-desc">讲座描述：{{ lecture.description }}</div>
          <div class="my-correct-rate">
            本次正确率：{{ myCorrectRate }}%
          </div>
        </div>
        <div class="quiz-list">
          <h3 class="section-title">题目与答题</h3>
          <ul>
            <li v-for="(quiz, idx) in quizzes" :key="quiz.id" class="quiz-card">
              <div class="quiz-question"><span class="quiz-index">Q{{ idx + 1 }}</span> {{ quiz.question }}</div>
              <ul class="quiz-options">
                <li v-for="opt in quiz.options" :key="opt.optionLabel">
                  <button
                    :disabled="quiz.answered"
                    @click="selectOption(quiz, opt.optionLabel)"
                    :class="{ selected: quiz.selected === opt.optionLabel }"
                  >
                    {{ opt.optionLabel }}. {{ opt.optionText }}
                  </button>
                </li>
              </ul>
              <button v-if="!quiz.answered" class="submit-btn" @click="submitAnswer(quiz)">提交</button>
              <span v-if="quiz.answered" :class="['feedback', quiz.feedback === '回答正确' ? 'correct' : 'wrong']">
                {{ quiz.feedback }}
              </span>
              <div class="quiz-stats">
                <span>答题人数：{{ quiz.stats.count }}</span>
                <span>正确率：{{ quiz.stats.correctRate }}%</span>
              </div>
            </li>
          </ul>
        </div>
        <!-- 反馈区域 -->
        <div class="feedback-section">
          <div>
            <span>题目质量：</span>
            <label v-for="opt in ['深奥','中庸','浅显']" :key="opt">
              <input type="radio" v-model="feedback.question_quality" :value="opt" :disabled="feedbackSubmitted" />{{ opt }}
            </label>
          </div>
          <div>
            <span>演讲质量：</span>
            <label v-for="opt in ['生动','尚可','枯燥']" :key="opt">
              <input type="radio" v-model="feedback.speech_quality" :value="opt" :disabled="feedbackSubmitted" />{{ opt }}
            </label>
          </div>
          <div>
            <span>演讲者水平：</span>
            <label v-for="opt in ['优秀','一般','欠缺']" :key="opt">
              <input type="radio" v-model="feedback.speaker_level" :value="opt" :disabled="feedbackSubmitted" />{{ opt }}
            </label>
          </div>
          <button class="feedback-submit-btn" @click="submitFeedback" :disabled="feedbackSubmitted">提交反馈</button>
          <span v-if="feedbackSubmitted" class="feedback-success">感谢您的反馈！</span>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import Header from '@/components/Header.vue'
import axios from 'axios'

const route = useRoute()
const lectureId = Number(route.params.id)
const lecture = ref({})
const quizzes = ref([])
const lectureStats = ref({})
const loading = ref(false)
const error = ref('')
const user = JSON.parse(localStorage.getItem('user') || '{}')
const userId = user.id

const feedback = ref({
  question_quality: '',
  speech_quality: '',
  speaker_level: ''
});
const feedbackSubmitted = ref(false);

async function loadDetail() {
  loading.value = true
  error.value = ''
  try {
    const res = await axios.get(`/lecture/detail/${lectureId}?role=audience`)
    const data = res.data.data
    lecture.value = data.lecture
    quizzes.value = data.quizzes || []
    lectureStats.value = data.lectureStats || {}
    quizzes.value.forEach(q => {
      q.selected = ''
      q.answered = false
      q.feedback = ''
    })
  } catch (e) {
    error.value = '加载失败，请重试'
  } finally {
    loading.value = false
  }
}

function selectOption(quiz, label) {
  quiz.selected = label
}

async function submitAnswer(quiz) {
  if (!quiz.selected) return
  const res = await axios.post('/answer/submit', {
    userId,
    quizId: quiz.id,
    selected: quiz.selected
  })
  quiz.answered = true
  quiz.feedback = res.data.data.correct ? '回答正确' : '回答错误'
  // 刷新统计
  const statsRes = await axios.get(`/quiz/stats/${quiz.id}`)
  quiz.stats = statsRes.data.data
  // 刷新讲座统计
  const lectureStatsRes = await axios.get(`/lecture/stats/${lectureId}`)
  lectureStats.value = lectureStatsRes.data.data
}

async function submitFeedback() {
  if (!feedback.value.question_quality || !feedback.value.speech_quality || !feedback.value.speaker_level) return;
  for (const [type, comment] of Object.entries(feedback.value)) {
    await axios.post('/feedback', {
      userId,
      lectureId,
      feedbackType: type,
      comment
    });
  }
  feedbackSubmitted.value = true;
}

const correctCount = computed(() =>
  quizzes.value.filter(q => q.answered && q.feedback === '回答正确').length
)
const answeredCount = computed(() =>
  quizzes.value.filter(q => q.answered).length
)
const myCorrectRate = computed(() =>
  answeredCount.value === 0 ? 0 : Math.round((correctCount.value / answeredCount.value) * 100)
)

onMounted(loadDetail)
</script>

<style scoped>
.lecture-detail-container {
  max-width: 900px;
  margin: 40px auto;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(67,97,238,0.10);
  padding: 32px 24px 24px 24px;
}
.lecture-title {
  color: #4361ee;
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 12px;
  text-align: center;
}
.lecture-info-card {
  background: #f4f7fe;
  border-radius: 12px;
  padding: 18px 20px;
  margin-bottom: 24px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.04);
}
.lecture-desc {
  color: #333;
  font-size: 1.1rem;
}
.section-title {
  color: #222;
  font-size: 1.2rem;
  margin-bottom: 12px;
  border-left: 4px solid #4361ee;
  padding-left: 10px;
}
.quiz-list {
  margin-top: 12px;
}
.quiz-card {
  background: #f8f9fa;
  border-radius: 10px;
  padding: 18px 16px 12px 16px;
  margin-bottom: 18px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.06);
}
.quiz-question {
  font-weight: bold;
  font-size: 1.1rem;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}
.quiz-index {
  background: #4361ee;
  color: #fff;
  border-radius: 50%;
  width: 28px;
  height: 28px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  margin-right: 10px;
  font-size: 1rem;
}
.quiz-options {
  margin-bottom: 10px;
  display: flex;
  gap: 12px;
}
.quiz-options button {
  background: #eaf0fb;
  border: none;
  border-radius: 6px;
  padding: 8px 18px;
  font-size: 1rem;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
}
.quiz-options button.selected,
.quiz-options button:active {
  background: #4361ee;
  color: #fff;
}
.quiz-options button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
.submit-btn {
  background: #4361ee;
  color: #fff;
  border: none;
  border-radius: 6px;
  padding: 7px 22px;
  font-size: 1rem;
  margin-bottom: 8px;
  cursor: pointer;
  margin-top: 2px;
}
.feedback {
  display: inline-block;
  margin-left: 12px;
  font-weight: bold;
  font-size: 1rem;
}
.feedback.correct {
  color: #27ae60;
}
.feedback.wrong {
  color: #e74c3c;
}
.quiz-stats {
  margin-top: 6px;
  color: #888;
  font-size: 0.98rem;
}
.loading {
  color: #4361ee;
  text-align: center;
  margin: 20px 0;
}
.error {
  color: #e74c3c;
  text-align: center;
  margin: 20px 0;
}
.my-correct-rate {
  margin-top: 10px;
  color: #4361ee;
  font-size: 1.08rem;
  font-weight: bold;
}
.feedback-section {
  margin: 32px 0 0 0;
  padding: 20px 24px;
  background: #f4f7fe;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.04);
  font-size: 1.08rem;
}
.feedback-section > div {
  margin-bottom: 12px;
}
.feedback-section label {
  margin-right: 18px;
  margin-left: 6px;
  cursor: pointer;
}
.feedback-submit-btn {
  background: #4361ee;
  color: #fff;
  border: none;
  border-radius: 8px;
  padding: 8px 32px;
  font-size: 1.08rem;
  font-weight: 500;
  margin-top: 10px;
  cursor: pointer;
  transition: background 0.2s;
}
.feedback-submit-btn:disabled {
  background: #bfc8e6;
  color: #fff;
  cursor: not-allowed;
}
.feedback-success {
  color: #27ae60;
  margin-left: 18px;
  font-weight: bold;
}
</style> 