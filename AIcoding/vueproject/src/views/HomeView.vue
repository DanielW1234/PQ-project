<template>
  <div class="home-container">
    <div class="home-card">
      <h1 class="home-title">欢迎来到 PopQuiz 智能课堂互动系统</h1>
      <p class="home-desc">让知识传递更高效，体验AI驱动的课堂互动！</p>
      <router-link to="/auth" class="home-btn">登录 / 注册</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f4f7fe;
}
.home-card {
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(67,97,238,0.12);
  padding: 48px 40px 40px 40px;
  text-align: center;
  max-width: 420px;
  width: 100%;
}
.home-title {
  font-size: 2rem;
  font-weight: bold;
  color: #222;
  margin-bottom: 18px;
}
.home-desc {
  color: #555;
  font-size: 1.1rem;
  margin-bottom: 32px;
}
.home-btn {
  display: inline-block;
  background: #4361ee;
  color: #fff;
  border-radius: 8px;
  padding: 14px 40px;
  font-size: 1.1rem;
  font-weight: 500;
  text-decoration: none;
  box-shadow: 0 2px 12px rgba(67,97,238,0.08);
  transition: background 0.2s, box-shadow 0.2s;
}
.home-btn:hover {
  background: #274bdb;
  box-shadow: 0 4px 24px rgba(67,97,238,0.16);
}
</style> 