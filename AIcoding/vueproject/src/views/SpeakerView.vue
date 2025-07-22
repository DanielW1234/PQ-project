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
            <span class="role-tag">演讲者视图</span>
          </div>
        </div>
        <div class="function-cards">
          <div class="function-card">
            <i class="fas fa-file-alt"></i>
            <h3>上传讲座内容</h3>
            <textarea v-model="lectureContent" placeholder="粘贴或输入讲座文本内容..." rows="6" style="width:100%;margin-bottom:10px;"></textarea>
            <button class="btn btn-primary" @click="generateQuiz" :disabled="loading || !lectureContent">
              <i class="fas fa-robot"></i> 一键AI生成题目
            </button>
            <div v-if="loading" style="color:#4361ee;margin-top:8px;">AI正在生成题目，请稍候...</div>
            <div v-if="error" style="color:red;margin-top:8px;">{{ error }}</div>
          </div>
        </div>
        <div v-if="quizzes.length" class="quiz-list">
          <h3 style="margin-top:30px;">已生成题目</h3>
          <div v-for="quiz in quizzes" :key="quiz.id" class="quiz-item">
            <div class="quiz-question">{{ quiz.questionText }}</div>
            <ul class="quiz-options">
              <li v-for="opt in quiz.options" :key="opt.optionLabel">
                <b>{{ opt.optionLabel }}.</b> {{ opt.optionText }}
                <span v-if="opt.optionLabel === quiz.correctOption" style="color:green;font-weight:bold;">（正确答案）</span>
              </li>
            </ul>
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
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import Header from '@/components/Header.vue'
import { aiGenerateQuizzes, getQuizzesByLecture, getQuizOptions } from '@/api/index.js'

export default {
  components: { Header },
  setup() {
    const router = useRouter()
    const username = ref('演讲者用户') // 实际应用中应从登录状态获取
    const lectureId = ref(1) // 演示用，实际应从讲座管理获取
    const lectureContent = ref('')
    const loading = ref(false)
    const error = ref('')
    const quizzes = ref([])

    const logout = () => {
      router.push({ name: 'home' })
    }

    const generateQuiz = async () => {
      if (!lectureContent.value) return
      loading.value = true
      error.value = ''
      try {
        const res = await aiGenerateQuizzes(lectureId.value, lectureContent.value)
        // 获取题目及选项
        const quizArr = await Promise.all(res.data.map(async q => {
          const optRes = await getQuizOptions(q.id)
          return {
            ...q,
            options: optRes.data
          }
        }))
        quizzes.value = quizArr
      } catch (e) {
        error.value = 'AI生成题目失败，请重试或联系管理员'
      } finally {
        loading.value = false
      }
    }

    return {
      username,
      lectureContent,
      loading,
      error,
      quizzes,
      logout,
      generateQuiz
    }
  }
}
</script>

<style scoped>
.quiz-list {
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
</style> 