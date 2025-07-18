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

export default {
  emits: ['register-success', 'switch-tab'],
  setup(props, { emit }) {
    const username = ref('')
    const password = ref('')
    const confirmPassword = ref('')
    const role = ref('')
    const error = ref('')
    const success = ref(false)
    
    const register = () => {
      // 重置状态
      error.value = ''
      success.value = false
      
      // 验证输入
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
      
      // 在实际应用中，这里应该发送API请求注册用户
      // 这里只是模拟注册过程
      
      // 注册成功
      success.value = true
      emit('register-success', {
        username: username.value,
        role: role.value
      })
      
      // 清空表单
      username.value = ''
      password.value = ''
      confirmPassword.value = ''
      
      // 3秒后切换到登录标签页
      setTimeout(() => {
        emit('switch-tab', 'login')
        success.value = false
      }, 3000)
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
