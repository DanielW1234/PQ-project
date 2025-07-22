<template>
  <div>
    <div v-if="success" class="success-message">
      <i class="fas fa-check-circle"></i>
      注册成功！请登录
    </div>
    
    <div v-if="error" class="error-message" style="text-align: center; margin-bottom: 20px;">
      {{ error }}
    </div>
    
    <div class="form-group">
      <label>用户名</label>
      <div class="input-with-icon">
        <i class="fas fa-user"></i>
        <input 
          type="text" 
          class="form-control" 
          v-model="username" 
          placeholder="请输入用户名"
          @focus="error = ''"
        >
      </div>
    </div>
    
    <div class="form-group">
      <label>密码</label>
      <div class="input-with-icon">
        <i class="fas fa-lock"></i>
        <input 
          type="password" 
          class="form-control" 
          v-model="password" 
          placeholder="请输入密码"
          @focus="error = ''"
        >
      </div>
    </div>
    
    <div class="form-group">
      <label>确认密码</label>
      <div class="input-with-icon">
        <i class="fas fa-lock"></i>
        <input 
          type="password" 
          class="form-control" 
          v-model="confirmPassword" 
          placeholder="请再次输入密码"
          @focus="error = ''"
        >
      </div>
    </div>
    
    <div class="form-group">
      <label>选择角色</label>
      <div class="role-options">
        <div 
          class="role-option" 
          :class="{ selected: role === 'audience' }"
          @click="role = 'audience'"
        >
          <div class="role-icon">👂</div>
          <span>听众</span>
        </div>
        <div 
          class="role-option" 
          :class="{ selected: role === 'speaker' }"
          @click="role = 'speaker'"
        >
          <div class="role-icon">🎤</div>
          <span>演讲者</span>
        </div>
        <div 
          class="role-option" 
          :class="{ selected: role === 'organizer' }"
          @click="role = 'organizer'"
        >
          <div class="role-icon">👔</div>
          <span>组织者</span>
        </div>
      </div>
    </div>
    
    <button class="btn btn-primary" @click="register">
      注册
    </button>
    
    <div class="switch-text">
      已有账号？<a @click="$emit('switch-tab', 'login')">立即登录</a>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { register as apiRegister } from '@/api/index.js'

export default {
  emits: ['register-success', 'switch-tab'],
  setup(props, { emit }) {
    const username = ref('')
    const password = ref('')
    const confirmPassword = ref('')
    const role = ref('')
    const error = ref('')
    const success = ref(false)
    
    const register = async () => {
      error.value = ''
      success.value = false
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
      try {
        const res = await apiRegister({ username: username.value, password: password.value, role: role.value })
        if (res.data.code === 'success') {
          success.value = true
          emit('register-success', res.data.user)
          username.value = ''
          password.value = ''
          confirmPassword.value = ''
          setTimeout(() => {
            emit('switch-tab', 'login')
            success.value = false
          }, 2000)
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
      success,
      register
    }
  }
}
</script> 