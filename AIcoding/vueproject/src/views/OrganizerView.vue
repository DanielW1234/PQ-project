<template>
  <div class="role-page-container">
    <header class="header-bar">
      <div class="brand">PopQuiz</div>
      <nav class="nav-links">
        <router-link to="/" class="nav-link">首页</router-link>
        <router-link to="/organizer" class="nav-link">组织者</router-link>
      </nav>
    </header>
    <div class="logout-bar">
      <button class="logout-btn" @click="logout">
        <i class="fas fa-sign-out-alt"></i> 退出登录
      </button>
    </div>
    <main class="main-content">
      <div class="welcome-info">
        <h2 class="welcome-title">欢迎回来，{{ organizer.username }}！</h2>
        <span class="role-tag">组织者视图</span>
      </div>
      <div class="user-info">
        <div><b>用户名：</b>{{ organizer.username }}</div>
      </div>
      <div class="lecture-list">
        <h3>我组织的讲座</h3>
        <div v-if="loading" style="color:#4361ee;">讲座加载中...</div>
        <div v-if="error" style="color:red;">{{ error }}</div>
        <div v-for="lecture in lectures" :key="lecture.id" class="lecture-card">
          <div class="lecture-title">
            讲座名称：
            <router-link
              :to="{ name: detailRouteName, params: { id: lecture.id } }"
              class="lecture-link"
            >
              {{ lecture.title }}
            </router-link>
          </div>
          <div class="lecture-desc">
            讲座描述：{{ lecture.description }}
          </div>
        </div>
      </div>
    </main>
    <footer class="footer-bar">
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
import { getLectures, getLectureStats } from '@/router/index.js'
import axios from 'axios'

export default {
  setup() {
    const organizer = ref(JSON.parse(localStorage.getItem('user') || '{"username":"组织者用户","id":1}'))
    const lectures = ref([])
    const loading = ref(false)
    const error = ref('')
    const router = useRouter()
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    let detailRouteName = 'AudienceLectureDetail'
    if (user.role === 'organizer') detailRouteName = 'OrganizerLectureDetail'
    if (user.role === 'speaker') detailRouteName = 'SpeakerLectureDetail'

    const loadLectures = async () => {
      loading.value = true
      error.value = ''
      try {
        const res = await getLectures()
        const lectureArr = await Promise.all(res.data.data.map(async lec => {
          const detailRes = await axios.get(`/lecture/${lec.id}/detail`)
          const detail = detailRes.data.data
          // 只保留讲座基本信息和题目统计
          return {
            ...detail.lecture,
            quizzes: detail.quizzes.map(q => ({
              questionText: q.quiz.questionText,
              answerCount: q.answerCount,
              correctRate: q.correctRate
            }))
          }
        }))
        lectures.value = lectureArr
      } catch (e) {
        error.value = '讲座加载失败，请重试'
      } finally {
        loading.value = false
      }
    }
    onMounted(loadLectures)
    const logout = () => {
      localStorage.removeItem('user')
      router.push({ name: 'auth' })
    }
    return {
      organizer,
      lectures,
      loading,
      error,
      logout,
      detailRouteName
    }
  }
}
</script>

<style scoped>
.role-page-container {
  min-height: 100vh;
  background: #fff;
  display: flex;
  flex-direction: column;
}
.header-bar {
  background: #4361ee;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  height: 56px;
}
.brand {
  font-size: 22px;
  font-weight: bold;
  letter-spacing: 2px;
}
.nav-links {
  display: flex;
  gap: 24px;
}
.nav-link {
  color: #fff;
  text-decoration: none;
  font-size: 16px;
  transition: color 0.2s;
}
.nav-link:hover {
  color: #ffd700;
}
.logout-bar {
  background: #f6f8fa;
  padding: 16px 32px 0 32px;
}
.logout-btn {
  background: #fff;
  color: #4361ee;
  border: 1.5px solid #4361ee;
  border-radius: 8px;
  padding: 8px 24px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
}
.logout-btn:hover {
  background: #4361ee;
  color: #fff;
}
.main-content {
  max-width: 800px;
  margin: 32px auto 0 auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 16px rgba(67,97,238,0.08);
  padding: 32px 24px 24px 24px;
}
.welcome-info {
  margin-bottom: 16px;
}
.welcome-title {
  font-size: 1.7rem;
  font-weight: bold;
  color: #222;
  margin-bottom: 4px;
}
.role-tag {
  color: #4361ee;
  font-size: 1.1rem;
  font-weight: 500;
}
.user-info {
  margin-bottom: 24px;
}
.lecture-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin: 32px 0;
}
.lecture-card {
  background: #f4f7fe;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(67,97,238,0.08);
  padding: 20px 28px 16px 28px;
  transition: box-shadow 0.2s;
}
.lecture-card:hover {
  box-shadow: 0 4px 24px rgba(67,97,238,0.16);
}
.lecture-title {
  font-size: 1.15rem;
  font-weight: bold;
  margin-bottom: 8px;
  color: #222;
}
.lecture-link {
  color: #4361ee;
  text-decoration: underline;
  margin-left: 6px;
  transition: color 0.2s;
}
.lecture-link:hover {
  color: #274bdb;
}
.lecture-desc {
  color: #555;
  font-size: 1rem;
  margin-left: 2px;
}
.lecture-stats span {
  margin-right: 24px;
}
.footer-bar {
  margin-top: 40px;
  background: #f6f8fa;
  padding: 24px 0 8px 0;
  text-align: center;
}
.footer-links {
  display: flex;
  justify-content: center;
  gap: 24px;
  margin-bottom: 8px;
}
.footer-links a {
  color: #4361ee;
  text-decoration: none;
  font-size: 15px;
  transition: color 0.2s;
}
.footer-links a:hover {
  color: #ffd700;
}
</style> 