<template>
  <div class="user-center-container">
    <Header />
    <main>
      <h2>用户中心</h2>
      <div class="user-info">
        <div><b>用户名：</b>{{ user.username }}</div>
        <div><b>角色：</b>{{ user.role }}</div>
      </div>
      <div class="user-stats">
        <h3>参与讲座</h3>
        <ul>
          <li v-for="lecture in lectures" :key="lecture.id">
            {{ lecture.title }}
          </li>
        </ul>
        <h3>答题统计</h3>
        <div>答题总数：{{ answerCount }}</div>
        <div>正确数：{{ correctCount }}</div>
        <div>正确率：{{ answerCount ? Math.round((correctCount/answerCount)*100) : 0 }}%</div>
      </div>
    </main>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import Header from '@/components/Header.vue'
import { getLectures, getAnswers } from '@/router/index.js'

export default {
  components: { Header },
  setup() {
    // 从localStorage/sessionStorage获取当前登录用户信息
    const user = ref(JSON.parse(localStorage.getItem('user') || '{}'))
    const lectures = ref([])
    const answerCount = ref(0)
    const correctCount = ref(0)

    const loadStats = async () => {
      const lecturesRes = await getLectures()
      lectures.value = lecturesRes.data.data
      const answersRes = await getAnswers()
      answerCount.value = answersRes.data.data.length
      correctCount.value = answersRes.data.data.filter(a => a.isCorrect).length
    }
    onMounted(loadStats)
    return {
      user,
      lectures,
      answerCount,
      correctCount
    }
  }
}
</script>

<style scoped>
.user-center-container {
  max-width: 600px;
  margin: 40px auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 16px rgba(67,97,238,0.08);
  padding: 32px 24px 24px 24px;
}
.user-info {
  margin-bottom: 24px;
}
.user-stats {
  margin-top: 24px;
}
</style> 