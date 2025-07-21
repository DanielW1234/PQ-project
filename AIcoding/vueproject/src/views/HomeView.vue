<template>
  <div class="home-container">
    <h1>欢迎来到 PopQuiz 智能课堂互动系统</h1>
    <p>让知识传递更高效，体验AI驱动的课堂互动！</p>
    <router-link to="/auth" class="btn btn-primary">登录 / 注册</router-link>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.home-container {
  text-align: center;
  margin-top: 100px;
}
.btn {
  margin-top: 32px;
  padding: 12px 32px;
  font-size: 18px;
}
</style> 