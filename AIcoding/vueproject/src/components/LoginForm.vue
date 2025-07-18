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
        <i class="fas fa-lock"></极狐i>
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

export default {
  emits: ['login-success', 'switch-tab'],
  setup(props, { emit }) {
    const router = useRouter()
    const username = ref('')
    const password = ref('')
    const role = ref('')
    const error = ref('')
    
    const login = () => {
      // 验证输入
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
      
      // 在实际应用中，这里应该发送API请求进行验证
      // 这里使用硬编码的演示用户
      const demoUsers = [
        { username: 'student', password: '123456', role: 'audience' },
        { username: 'teacher', password: '123456', role: 'speaker' },
        { username: 'admin', password: '123456', role: 'organizer' }
      ]
      
      const user = demoUsers.find(u => 
        u.username === username.value && 
        u.password === password.value &&
        u.role === role.value
      )
      
      if (user) {
        // 登录成功
        emit('login-success', user)
        router.push({ name: role.value })
      } else {
        error.value = '用户名或密码错误，或角色不匹配'
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
