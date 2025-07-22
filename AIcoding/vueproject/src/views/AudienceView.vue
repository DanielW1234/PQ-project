<template>
  <div>
    <Header />
    <main>
      <div class="role-view">
        <div class="role-header">
          <button class="logout-btn" @click="logout">
            <i class="fas fa-sign-out-alt"></i> 退出登录
          </button>
          <div>
            <h2 class="welcome-title">欢迎回来，{{ username }}！</h2>
            <span class="role-tag">听众视图</span>
          </div>
        </div>
        <div class="quiz-section">
          <h3>参与测验</h3>
          <div v-if="loading" style="color:#4361ee;">题目加载中...</div>
          <div v-if="error" style="color:red;">{{ error }}</div>
          <div v-for="quiz in quizzes" :key="quiz.id" class="quiz-item">
            <div class="quiz-question">{{ quiz.questionText }}</div>
            <ul class="quiz-options">
              <li v-for="opt in quiz.options" :key="opt.optionLabel">
                <label>
                  <input type="radio" :name="'quiz-'+quiz.id" :value="opt.optionLabel" v-model="quiz.userAnswer" :disabled="quiz.answered">
                  <b>{{ opt.optionLabel }}.</b> {{ opt.optionText }}
                </label>
              </li>
            </ul>
            <button class="btn btn-primary" @click="submitAnswer(quiz)" :disabled="quiz.answered || !quiz.userAnswer">提交答案</button>
            <span v-if="quiz.answered">
              <span v-if="quiz.userAnswer === quiz.correctOption" style="color:green;font-weight:bold;">答对了！</span>
              <span v-else style="color:red;font-weight:bold;">答错了，正确答案：{{ quiz.correctOption }}</span>
            </span>
            <div class="quiz-feedback">
              <input v-model="quiz.feedback" placeholder="对本题的反馈..." :disabled="quiz.answered" style="margin-right:8px;">
              <button class="btn btn-secondary" @click="submitFeedback(quiz)" :disabled="quiz.answered || !quiz.feedback">提交反馈</button>
            </div>
            <div class="quiz-discussion">
              <h4>讨论区</h4>
              <ul>
                <li v-for="d in quiz.discussions" :key="d.id">{{ d.content }}</li>
              </ul>
              <input v-model="quiz.newDiscussion" placeholder="发表评论..." style="margin-right:8px;">
              <button class="btn btn-secondary" @click="submitDiscussion(quiz)">发送</button>
            </div>
          </div>
        </div>
      </div>
    </main>
    <footer>
      <div class="footer-links">
        <a href="#">关于我们</a>
        <a href="#">隐私政策</a>
        <a href="#">使用条款</a>
        <a href="#">帮助中心</a>
        <a href="#">联系我们</a>
      </div>
      <p>© 2025 PopQuiz 智能课堂互动系统 | 让知识传递更高效</p>
    </footer>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Header from '@/components/Header.vue'
import { getQuizzesByLecture, getQuizOptions, submitAnswer, submitFeedback, getDiscussionsByQuiz, submitDiscussion } from '@/api/index.js'

export default {
  components: { Header },
  setup() {
    const router = useRouter()
    const username = ref('听众用户') // 实际应用中应从登录状态获取
    const lectureId = ref(1) // 演示用，实际应从讲座管理获取
    const quizzes = ref([])
    const loading = ref(false)
    const error = ref('')

    const logout = () => {
      router.push({ name: 'home' })
    }

    const loadQuizzes = async () => {
      loading.value = true
      error.value = ''
      try {
        const res = await getQuizzesByLecture(lectureId.value)
        const quizArr = await Promise.all(res.data.map(async q => {
          const optRes = await getQuizOptions(q.id)
          const discRes = await getDiscussionsByQuiz(q.id)
          return {
            ...q,
            options: optRes.data,
            userAnswer: '',
            answered: false,
            feedback: '',
            discussions: discRes.data || [],
            newDiscussion: ''
          }
        }))
        quizzes.value = quizArr
      } catch (e) {
        error.value = '题目加载失败，请重试'
      } finally {
        loading.value = false
      }
    }

    const submitAnswerFn = async (quiz) => {
      if (!quiz.userAnswer) return
      try {
        await submitAnswer({ quizId: quiz.id, userId: 1, chosenOption: quiz.userAnswer, isCorrect: quiz.userAnswer === quiz.correctOption })
        quiz.answered = true
      } catch (e) {
        alert('提交答案失败')
      }
    }

    const submitFeedbackFn = async (quiz) => {
      if (!quiz.feedback) return
      try {
        await submitFeedback({ quizId: quiz.id, userId: 1, lectureId: lectureId.value, feedbackType: '题目反馈', comment: quiz.feedback })
        quiz.feedback = ''
        alert('反馈已提交')
      } catch (e) {
        alert('提交反馈失败')
      }
    }

    const submitDiscussionFn = async (quiz) => {
      if (!quiz.newDiscussion) return
      try {
        await submitDiscussion({ quizId: quiz.id, userId: 1, content: quiz.newDiscussion })
        quiz.discussions.push({ content: quiz.newDiscussion })
        quiz.newDiscussion = ''
      } catch (e) {
        alert('发送评论失败')
      }
    }

    onMounted(loadQuizzes)

    return {
      username,
      quizzes,
      loading,
      error,
      logout,
      submitAnswer: submitAnswerFn,
      submitFeedback: submitFeedbackFn,
      submitDiscussion: submitDiscussionFn
    }
  }
}
</script>

<style scoped>
.quiz-section {
  margin-top: 20px;
}
.quiz-item {
  background: #f8f9fa;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
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
.quiz-feedback, .quiz-discussion {
  margin-top: 8px;
}
</style> 