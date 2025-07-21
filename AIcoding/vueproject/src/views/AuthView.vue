<template>
  <div class="auth-container">
    <div class="tabs">
      <div 
        class="tab" 
        :class="{ active: activeTab === 'login' }"
        @click="activeTab = 'login'"
      >
        登录
      </div>
      <div 
        class="tab" 
        :class="{ active: activeTab === 'register' }"
        @click="activeTab = 'register'"
      >
        注册
      </div>
    </div>
    
    <LoginForm 
      v-if="activeTab === 'login'" 
      @login-success="handleLoginSuccess"
      @switch-tab="activeTab = 'register'"
    />
    <RegisterForm 
      v-if="activeTab === 'register'" 
      @register-success="handleRegisterSuccess"
      @switch-tab="activeTab = 'login'"
    />
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import LoginForm from '@/components/LoginForm.vue'
import RegisterForm from '@/components/RegisterForm.vue'

export default {
  components: {
    LoginForm,
    RegisterForm
  },
  setup() {
    const activeTab = ref('login')
    const router = useRouter()
    
    const handleLoginSuccess = (user) => {
      // 登录成功后跳转到角色视图
      router.push({ name: user.role })
    }
    
    const handleRegisterSuccess = (user) => {
      // 注册成功后切换到登录标签页
      activeTab.value = 'login'
    }
    
    return {
      activeTab,
      handleLoginSuccess,
      handleRegisterSuccess
    }
  }
}
</script>

<style scoped>
.auth-container {
  max-width: 400px;
  margin: 80px auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 16px rgba(67,97,238,0.08);
  padding: 32px 24px 24px 24px;
}
.tabs {
  display: flex;
  margin-bottom: 24px;
}
.tab {
  flex: 1;
  text-align: center;
  padding: 12px 0;
  cursor: pointer;
  font-weight: bold;
  border-bottom: 2px solid #eee;
  color: #888;
  transition: all 0.2s;
}
.tab.active {
  color: #4361ee;
  border-bottom: 2px solid #4361ee;
}
</style> 