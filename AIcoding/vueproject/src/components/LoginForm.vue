<template>
  <div>
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
    
    <button class="btn btn-primary" @click="login">
      登录
    </button>
    
    <div class="switch-text">
      没有账号？<a @click="$emit('switch-tab', 'register')">立即注册</a>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login as apiLogin } from '@/api/index.js'

export default {
  emits: ['login-success', 'switch-tab'],
  setup(props, { emit }) {
    const router = useRouter()
    const username = ref('')
    const password = ref('')
    const role = ref('')
    const error = ref('')
    
    const login = async () => {
      if (!username.value) {
        error.value = '请输入用户名'
        return
      }
      if (!password.value) {
        error.value = '请输入密码'
        return
      }
      if (!role.value) {
        error.value = '请选择角色'
        return
      }
      try {
        const res = await apiLogin({ username: username.value, password: password.value, role: role.value })
        if (res.data.code === 'success') {
          emit('login-success', res.data.user)
          router.push({ name: res.data.user.role })
        } else {
          error.value = res.data.msg || '用户名或密码错误，或角色不匹配'
        }
      } catch (e) {
        error.value = '登录失败，请检查网络或稍后重试'
      }
    }
    
    return {
      username,
      password,
      role,
      error,
      login
    }
  }
}
</script> 