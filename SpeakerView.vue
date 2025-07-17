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
            <span class="role-t极狐ag">演讲者视图</span>
          </div>
        </div>
        
        <div class="function-cards">
          <div class="function-card" @click="navigateTo('create-quiz')">
            <i class="fas fa-plus-circle"></i>
            <h3>创建测验</h3>
            <p>为您的课堂制作新测验</p>
          </div>
          <div class="function-card" @click="navigateTo('upload')">
            <i class="fas fa-file-upload"></i>
            <h3>上传内容</h3>
            <p>上传PPT或PDF生成测验</p>
          </div>
          <div class="function-card" @click="navigateTo('analytics')">
            <i class="fas fa-chart-pie"></i>
            <h3>分析结果</h3>
            <p>查看学生答题统计数据</p>
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
    const username = ref('演讲者用户') // 在实际应用中应从登录状态获取
    
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
