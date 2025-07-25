<template>
  <div class="register-container">
    <div class="tabs">
      <div :class="['tab', activeTab === 'login' ? 'active' : '']" @click="$emit('switch-tab', 'login')">登录</div>
      <div :class="['tab', activeTab === 'register' ? 'active' : '']" @click="$emit('switch-tab', 'register')">注册</div>
    </div>
    <form class="register-form" @submit.prevent="register">
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
      <div class="form-group">
        <label class="input-label">确认密码</label>
        <div class="input-with-icon">
          <i class="fas fa-lock"></i>
          <input type="password" class="form-control" v-model="confirmPassword" placeholder="请再次输入密码" @focus="error = ''" />
        </div>
      </div>
      <div class="form-group">
        <label class="input-label">选择角色</label>
        <div class="role-options">
          <div class="role-option" :class="{ selected: role === 'audience' }" @click="role = 'audience'">
            <div class="role-icon">👂</div>
            <span>听众</span>
          </div>
          <div class="role-option" :class="{ selected: role === 'speaker' }" @click="role = 'speaker'">
            <div class="role-icon">🎤</div>
            <span>演讲者</span>
          </div>
          <div class="role-option" :class="{ selected: role === 'organizer' }" @click="role = 'organizer'">
            <div class="role-icon">👔</div>
            <span>组织者</span>
          </div>
        </div>
      </div>
      <div class="form-group terms-group">
        <label class="terms-label">
          <input type="checkbox" v-model="agreed" />
          我已阅读并同意
          <a href="#" class="terms-link">服务条款</a>和
          <a href="#" class="terms-link">隐私政策</a>
        </label>
      </div>
      <button class="register-btn" type="submit" :disabled="!canRegister">注册</button>
      <div v-if="error" class="error-message">{{ error }}</div>
    </form>
    <div class="switch-text">
      已有账号？<a class="switch-link" @click="$emit('switch-tab', 'login')">立即登录</a>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { register as apiRegister } from '@/router/index.js'

export default {
  emits: ['register-success', 'switch-tab'],
  props: {
    activeTab: {
      type: String,
      default: 'register'
    }
  },
  setup(props, { emit }) {
    const username = ref('')
    const password = ref('')
    const confirmPassword = ref('')
    const role = ref('audience')
    const error = ref('')
    const agreed = ref(false)
    const canRegister = computed(() => {
      return (
        username.value &&
        password.value &&
        confirmPassword.value &&
        password.value === confirmPassword.value &&
        role.value &&
        agreed.value &&
        password.value.length >= 6
      )
    })
    const register = async () => {
      error.value = ''
      if (!username.value) {
        error.value = '请输入用户名'
        return
      }
      if (!password.value) {
        error.value = '请输入密码'
        return
      }
      if (password.value.length < 6) {
        error.value = '密码长度至少6位'
        return
      }
      if (password.value !== confirmPassword.value) {
        error.value = '两次输入的密码不一致'
        return
      }
      if (!role.value) {
        error.value = '请选择角色'
        return
      }
      if (!agreed.value) {
        error.value = '请同意服务条款和隐私政策'
        return
      }
      try {
        const res = await apiRegister({ username: username.value, password: password.value, role: role.value })
        if (res.data.code === 'success' || res.data.code === '200' || res.data.code === 200) {
          emit('register-success', { username: username.value, role: role.value })
          username.value = ''
          password.value = ''
          confirmPassword.value = ''
        } else {
          error.value = res.data.msg || '注册失败'
        }
      } catch (e) {
        error.value = '注册失败，请检查网络或稍后重试'
      }
    }
    return {
      username,
      password,
      confirmPassword,
      role,
      error,
      agreed,
      canRegister,
      register
    }
  }
}
</script>

<style scoped>
.register-container {
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
.register-form {
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
.role-options {
  display: flex;
  gap: 12px;
  margin-top: 4px;
}
.role-option {
  background: #f3f4f8;
  border-radius: 8px;
  padding: 10px 18px;
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 15px;
  color: #222;
  cursor: pointer;
  border: 2px solid transparent;
  transition: border 0.2s, background 0.2s;
}
.role-option.selected {
  border: 2px solid #4361ee;
  background: #e3eaff;
}
.role-icon {
  font-size: 1.2em;
}
.terms-group {
  margin-top: 8px;
}
.terms-label {
  font-size: 14px;
  color: #888;
  display: flex;
  align-items: center;
  gap: 4px;
}
.terms-link {
  color: #4361ee;
  text-decoration: underline;
  cursor: pointer;
  margin: 0 2px;
  transition: color 0.2s;
}
.terms-link:hover {
  color: #233a7c;
}
.register-btn {
  margin-top: 10px;
  background: #bfc8e6;
  color: #fff;
  border: none;
  border-radius: 8px;
  padding: 12px 0;
  font-size: 1.1rem;
  font-weight: 500;
  cursor: not-allowed;
  transition: background 0.2s;
}
.register-btn:enabled {
  background: #4361ee;
  cursor: pointer;
}
.register-btn:enabled:hover {
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