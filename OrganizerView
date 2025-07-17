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
            <span class="role-tag">组织者视图</span>
          </div>
        </div>
        
        <div class="function-cards">
          <div class="function-card" @click="navigateTo('schedule')">
            <i class="fas fa-calendar-alt"></i>
            <h3>安排会议</h3>
            <p>创建和管理课堂时间表</p>
          </div>
          <div class="function-card" @click="navigateTo('user-management')">
            <i class="fas fa-users"></i>
            <h3>管理用户</h3>
            <p>管理演讲者和听众账户</p>
          </div>
          <div class="function-card" @click="navigateTo('reports')">
            <i class="fas fa-chart-line"></i>
            <h3>总体统计</h3>
            <p>查看所有活动的分析报告</p>
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

export default {
  components: {
    Header
  },
  setup() {
    const router = useRouter()
    const username = ref('组织者用户') // 在实际应用中应从登录状态获取
    
    const logout = () => {
      // 清除登录状态
      router.push({ name: 'home' })
    }
    
    const navigateTo = (routeName) => {
      router.push({ name: routeName })
    }
    
    return {
      username,
      logout,
      navigateTo
    }
  }
}
</script>
