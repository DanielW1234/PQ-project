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
        
        <div class="function-cards">
          <div class="function-card">
            <i class="fas fa-question-circle"></i>
            <h3>参加测验</h3>
            <p>参与课堂中的实时测验</p>
          </div>
          <div class="function-card">
            <i class="fas fa-chart-bar"></i>
            <h3>查看成绩</h3>
            <p>访问您的测验成绩和分析</p>
          </div>
          <div class="function-card">
            <i class="fas fa-comments"></i>
            <h3>参与讨论</h3>
            <p>加入课堂讨论区</p>
          </div>
        </div>
      </div>
    </main>
    <footer>
      <极狐div class="footer-links">
        <a href="#">关于我们</a>
        <a href="#">隐私政策</a>
        <a href="#">使用条款</a>
       极狐 <a href="#">帮助中心</a>
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

export default {
  components: {
    Header
  },
  setup() {
    const router = useRouter()
    const username = ref('听众用户') // 在实际应用中应从登录状态获取
    
    const logout = () => {
      // 清除登录状态
      // 在实际应用中应清除 token 等
      router.push({ name: 'home' })
    }
    
    return {
      username,
      logout
    }
  }
}
</script>