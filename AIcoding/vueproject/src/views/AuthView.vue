<template>
  <div class="auth-container">
    <LoginForm v-if="activeTab === 'login'" :activeTab="activeTab" @login-success="handleLoginSuccess" @switch-tab="switchTab" />
    <RegisterForm v-if="activeTab === 'register'" :activeTab="activeTab" @register-success="handleRegisterSuccess" @switch-tab="switchTab" />
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
      if (user.role === 'audience') {
        router.push({ name: 'audience' })
      } else if (user.role === 'speaker') {
        router.push({ name: 'speaker' })
      } else if (user.role === 'organizer') {
        router.push({ name: 'organizer' })
      } else {
        router.push({ name: 'home' })
      }
    }

    const handleRegisterSuccess = (user) => {
      activeTab.value = 'login'
    }

    const switchTab = (tab) => {
      activeTab.value = tab
    }

    return {
      activeTab,
      handleLoginSuccess,
      handleRegisterSuccess,
      switchTab
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
</style> 