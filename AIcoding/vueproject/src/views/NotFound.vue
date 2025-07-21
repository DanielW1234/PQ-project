<template>
  <div class="notfound-container">
    <h1>404</h1>
    <p>页面未找到</p>
    <router-link to="/" class="btn btn-primary">返回首页</router-link>
  </div>
</template>

<script>
export default {
  name: 'NotFound'
}
</script>

<style scoped>
.notfound-container {
  text-align: center;
  margin-top: 120px;
}
h1 {
  font-size: 80px;
  color: #4361ee;
}
p {
  font-size: 22px;
  color: #888;
}
.btn {
  margin-top: 32px;
  padding: 12px 32px;
  font-size: 18px;
}
</style> 