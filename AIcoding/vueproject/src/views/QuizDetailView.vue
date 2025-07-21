<template>
  <div class="quiz-detail-container">
    <Header />
    <main>
      <div v-if="loading" style="color:#4361ee;">加载中...</div>
      <div v-if="error" style="color:red;">{{ error }}</div>
      <div v-if="quiz">
        <h2>题目详情</h2>
        <div class="quiz-question">{{ quiz.questionText }}</div>
        <ul class="quiz-options">
          <li v-for="opt in options" :key="opt.optionLabel">
            <b>{{ opt.optionLabel }}.</b> {{ opt.optionText }}
            <span v-if="opt.optionLabel === quiz.correctOption" style="color:green;font-weight:bold;">（正确答案）</span>
          </li>
        </ul>
        <h3>讨论区</h3>
        <ul>
          <li v-for="d in discussions" :key="d.id">{{ d.content }}</li>
        </ul>
        <input v-model="newDiscussion" placeholder="发表评论..." style="margin-right:8px;">
        <button class="btn btn-secondary" @click="submitDiscussion">发送</button>
        <h3>反馈</h3>
        <input v-model="feedback" placeholder="对本题的反馈..." style="margin-right:8px;">
        <button class="btn btn-secondary" @click="submitFeedback">提交反馈</button>
      </div>
    </main>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Header from '@/components/Header.vue'
import { getQuizzesByLecture, getQuizOptions, getDiscussionsByQuiz, submitDiscussion, submitFeedback } from '@/api/index.js'

export default {
  components: { Header },
  setup() {
    const route = useRoute()
    const quizId = Number(route.params.id)
    const quiz = ref(null)
    const options = ref([])
    const discussions = ref([])
    const newDiscussion = ref('')
    const feedback = ref('')
    const loading = ref(false)
    const error = ref('')

    const loadDetail = async () => {
      loading.value = true
      error.value = ''
      try {
        // 这里假设你有API可以通过quizId获取题目详情
        // 这里只做演示，实际应补充getQuizById
        const quizList = await getQuizzesByLecture(1) // 假设lectureId=1
        quiz.value = quizList.data.find(q => q.id === quizId)
        const optRes = await getQuizOptions(quizId)
        options.value = optRes.data
        const discRes = await getDiscussionsByQuiz(quizId)
        discussions.value = discRes.data
      } catch (e) {
        error.value = '加载失败'
      } finally {
        loading.value = false
      }
    }
    const submitDiscussionFn = async () => {
      if (!newDiscussion.value) return
      await submitDiscussion({ quizId, userId: 1, content: newDiscussion.value })
      discussions.value.push({ content: newDiscussion.value })
      newDiscussion.value = ''
    }
    const submitFeedbackFn = async () => {
      if (!feedback.value) return
      await submitFeedback({ quizId, userId: 1, feedbackType: '题目反馈', comment: feedback.value })
      feedback.value = ''
      alert('反馈已提交')
    }
    onMounted(loadDetail)
    return {
      quiz,
      options,
      discussions,
      newDiscussion,
      feedback,
      loading,
      error,
      submitDiscussion: submitDiscussionFn,
      submitFeedback: submitFeedbackFn
    }
  }
}
</script>

<style scoped>
.quiz-detail-container {
  max-width: 700px;
  margin: 40px auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 16px rgba(67,97,238,0.08);
  padding: 32px 24px 24px 24px;
}
.quiz-question {
  font-weight: bold;
  margin-bottom: 8px;
}
.quiz-options {
  list-style: none;
  padding-left: 0;
}
.quiz-options li {
  margin-bottom: 4px;
}
</style> 