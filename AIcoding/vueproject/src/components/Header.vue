<template>
  <header class="header">
    <div class="logo" @click="goHome">
      <span class="logo-text">PopQuiz</span>
    </div>
    <nav class="nav">
      <router-link to="/" class="nav-link">首页</router-link>
      <router-link v-if="!role" to="/auth" class="nav-link">登录/注册</router-link>
      <router-link v-if="role === 'audience'" to="/audience" class="nav-link">听众</router-link>
      <router-link v-if="role === 'speaker'" to="/speaker" class="nav-link">演讲者</router-link>
      <router-link v-if="role === 'organizer'" to="/organizer" class="nav-link">组织者</router-link>
    </nav>
  </header>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'Header',
  setup() {
    const router = useRouter()
    const goHome = () => {
      router.push({ name: 'home' })
    }
    // 获取当前登录用户
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    // 角色判断
    const role = user.role || ''
    return { goHome, role }
  }
}
</script>

<style scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #4361ee;
  color: #fff;
  padding: 0 32px;
  height: 56px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.08);
}
.logo {
  font-size: 22px;
  font-weight: bold;
  cursor: pointer;
}
.logo-text {
  color: #fff;
  letter-spacing: 2px;
}
.nav {
  display: flex;
  gap: 24px;
}
.nav-link {
  color: #fff;
  text-decoration: none;
  font-size: 16px;
  transition: color 0.2s;
}
.nav-link:hover {
  color: #ffd700;
}
</style> 