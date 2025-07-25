<template>
  <div class="login-container">
    <div class="tabs">
      <div :class="['tab', activeTab === 'login' ? 'active' : '']" @click="$emit('switch-tab', 'login')">登录</div>
      <div :class="['tab', activeTab === 'register' ? 'active' : '']" @click="$emit('switch-tab', 'register')">注册</div>
    </div>
    <form class="login-form" @submit.prevent="login">
      <div class="form-group">
        <label class="input-label">用户名</label>
        <div class="input-with-icon">
          <i class="fas fa-user"></i>
          <input type="text" class="form-control" v-model="username" placeholder="请输入用户名" @focus="error = ''" />
        </div>
      </div>
      <div class="form-group">
        <label class="input-label">密码</label>
        <div class="input-with-icon">
          <i class="fas fa-lock"></i>
          <input type="password" class="form-control" v-model="password" placeholder="请输入密码" @focus="error = ''" />
        </div>
      </div>
      <button class="login-btn" type="submit">登录</button>
      <div v-if="error" class="error-message">{{ error }}</div>
    </form>
    <div class="switch-text">
      没有账号？<a class="switch-link" @click="$emit('switch-tab', 'register')">立即注册</a>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login as apiLogin } from '@/router/index.js'

export default {
  emits: ['login-success', 'switch-tab'],
  props: {
    activeTab: {
      type: String,
      default: 'login'
    }
  },
  setup(props, { emit }) {
    const router = useRouter()
    const username = ref('')
    const password = ref('')
    const error = ref('')

    const login = async () => {
      error.value = ''
      if (!username.value) {
        error.value = '请输入用户名'
        return
      }
      if (!password.value) {
        error.value = '请输入密码'
        return
      }
      try {
        const res = await apiLogin({ username: username.value, password: password.value })
        if (res.data.code === '200' || res.data.code === 200) {
          const user = res.data.data || {}
          localStorage.setItem('user', JSON.stringify(user))
          if (user.role === 'audience') {
            router.push({ name: 'audience' })
          } else if (user.role === 'speaker') {
            router.push({ name: 'speaker' })
          } else if (user.role === 'organizer') {
            router.push({ name: 'organizer' })
          } else {
            router.push({ name: 'home' })
          }
          emit('login-success', user)
        } else {
          error.value = res.data.msg || '用户名或密码错误'
        }
      } catch (e) {
        error.value = '登录失败，请检查网络或稍后重试'
      }
    }
    return {
      username,
      password,
      error,
      login
    }
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 80px auto 0 auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 16px rgba(67,97,238,0.08);
  padding: 32px 24px 24px 24px;
}
.tabs {
  display: flex;
  margin-bottom: 24px;
  border-bottom: 2px solid #eee;
}
.tab {
  flex: 1;
  text-align: center;
  padding: 12px 0;
  cursor: pointer;
  font-weight: bold;
  color: #888;
  border-bottom: 2px solid transparent;
  transition: color 0.2s, border-bottom 0.2s;
}
.tab.active {
  color: #4361ee;
  border-bottom: 2px solid #4361ee;
}
.login-form {
  display: flex;
  flex-direction: column;
  gap: 18px;
}
.form-group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}
.input-label {
  font-size: 15px;
  color: #222;
  margin-bottom: 2px;
}
.input-with-icon {
  display: flex;
  align-items: center;
  background: #f6f8fa;
  border: 1.5px solid #e0e0e0;
  border-radius: 8px;
  padding: 8px 12px;
  font-size: 16px;
}
.input-with-icon i {
  color: #bfc8e6;
  margin-right: 8px;
  font-size: 1.1em;
}
.form-control {
  border: none;
  outline: none;
  background: transparent;
  font-size: 16px;
  flex: 1;
}
.login-btn {
  margin-top: 10px;
  background: #4361ee;
  color: #fff;
  border: none;
  border-radius: 8px;
  padding: 12px 0;
  font-size: 1.1rem;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s;
}
.login-btn:hover {
  background: #233a7c;
}
.switch-text {
  margin-top: 18px;
  text-align: center;
  color: #888;
  font-size: 15px;
}
.switch-link {
  color: #4361ee;
  text-decoration: underline;
  cursor: pointer;
  margin-left: 4px;
  transition: color 0.2s;
}
.switch-link:hover {
  color: #233a7c;
}
.error-message {
  color: #e74c3c;
  margin-top: 8px;
  text-align: center;
  font-size: 15px;
}
</style> 