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
