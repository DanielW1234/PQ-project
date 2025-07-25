<template>
  <div class="lecture-detail-container">
    <Header />
    <main>
      <h2 class="lecture-title">讲座名称：{{ lecture.title }}</h2>
      <div v-if="loading" class="loading">加载中...</div>
      <div v-if="error" class="error">{{ error }}</div>
      <div v-if="lecture">
        <div class="lecture-info-card">
          <div class="lecture-desc">讲座描述：{{ lecture.description }}</div>
        </div>
        <div class="quiz-list">
          <h3 class="section-title">题目与答题统计</h3>
          <ul>
            <li v-for="(quiz, idx) in quizzes" :key="quiz.id" class="quiz-card">
              <div class="quiz-question"><span class="quiz-index">Q{{ idx + 1 }}</span> {{ quiz.question }}</div>
              <ul class="quiz-options">
                <li v-for="opt in quiz.options" :key="opt.optionLabel">
                  {{ opt.optionLabel }}. {{ opt.optionText }}
                </li>
              </ul>
              <div class="answer">正确答案：{{ quiz.answer }}</div>
              <div class="quiz-stats">
                <span>答题人数：{{ quiz.stats.count }}</span>
                <span>正确率：{{ quiz.stats.correctRate }}%</span>
              </div>
            </li>
          </ul>
        </div>
        <!-- 反馈统计区域 -->
        <div class="feedback-stats-section">
          <h3 class="section-title">听众反馈统计</h3>
          <div class="feedback-charts">
            <div class="feedback-chart" ref="questionChart" style="width: 260px; height: 220px;"></div>
            <div class="feedback-chart" ref="speechChart" style="width: 260px; height: 220px;"></div>
            <div class="feedback-chart" ref="levelChart" style="width: 260px; height: 220px;"></div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Header from '@/components/Header.vue'
import axios from 'axios'
import * as echarts from 'echarts';
import { nextTick } from 'vue';

const route = useRoute()
const lectureId = Number(route.params.id)
const lecture = ref({})
const quizzes = ref([])
const lectureStats = ref({})
const loading = ref(false)
const error = ref('')
const user = JSON.parse(localStorage.getItem('user') || '{}')

const questionChart = ref(null);
const speechChart = ref(null);
const levelChart = ref(null);

async function loadDetail() {
  loading.value = true
  error.value = ''
  try {
    const res = await axios.get(`/lecture/detail/${lectureId}?role=${user.role}`)
    const data = res.data.data
    lecture.value = data.lecture
    quizzes.value = data.quizzes || []
    lectureStats.value = data.lectureStats || {}
  } catch (e) {
    error.value = '加载失败，请重试'
  } finally {
    loading.value = false
  }
}

async function loadFeedbackStats() {
  const res = await axios.get(`/feedback/stats/${lectureId}`);
  const stats = res.data.data;
  console.log('反馈统计数据', stats);
  // 渲染饼图
  nextTick(() => {
    renderPie(questionChart.value, '题目质量', stats.question_quality, [
      { value: '深奥', color: '#ff9900' },
      { value: '中庸', color: '#27ae60' },
      { value: '浅显', color: '#8e44ad' }
    ]);
    renderPie(speechChart.value, '演讲质量', stats.speech_quality, [
      { value: '生动', color: '#ff9900' },
      { value: '尚可', color: '#27ae60' },
      { value: '枯燥', color: '#8e44ad' }
    ]);
    renderPie(levelChart.value, '演讲者水平', stats.speaker_level, [
      { value: '优秀', color: '#ff9900' },
      { value: '一般', color: '#27ae60' },
      { value: '欠缺', color: '#8e44ad' }
    ]);
  });
}

function renderPie(dom, title, statArr, colorMap) {
  console.log('渲染饼图', title, statArr, colorMap, dom);
  if (!dom) { console.warn('ECharts 容器未找到', title); return; }
  const total = (statArr || []).reduce((sum, item) => sum + (item.count || 0), 0);
  const data = colorMap.map(cfg => {
    const found = (statArr || []).find(i => i.comment === cfg.value);
    return {
      value: found ? found.count : 0,
      name: cfg.value,
      itemStyle: { color: cfg.color }
    };
  });
  const chart = echarts.init(dom);
  chart.setOption({
    title: { text: title, left: 'center', top: 10, textStyle: { fontSize: 15 } },
    tooltip: { trigger: 'item', formatter: '{b}: {c} ({d}%)' },
    series: [{
      type: 'pie',
      radius: ['45%', '70%'],
      avoidLabelOverlap: false,
      label: { show: true, position: 'outside', formatter: '{b}\n{d}%' },
      data
    }],
    graphic: total === 0 ? [{
      type: 'text',
      left: 'center',
      top: 'middle',
      style: { text: '暂无数据', fontSize: 16, fill: '#aaa' }
    }] : []
  });
}

onMounted(async () => {
  await loadDetail();
  await loadFeedbackStats();
});
</script>

<style scoped>
.lecture-detail-container {
  max-width: 900px;
  margin: 40px auto;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(67,97,238,0.10);
  padding: 32px 24px 24px 24px;
}
.lecture-title {
  color: #4361ee;
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 12px;
  text-align: center;
}
.lecture-info-card {
  background: #f4f7fe;
  border-radius: 12px;
  padding: 18px 20px;
  margin-bottom: 24px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.04);
}
.lecture-desc {
  color: #333;
  font-size: 1.1rem;
}
.section-title {
  color: #222;
  font-size: 1.2rem;
  margin-bottom: 12px;
  border-left: 4px solid #4361ee;
  padding-left: 10px;
}
.quiz-list {
  margin-top: 12px;
}
.quiz-card {
  background: #f8f9fa;
  border-radius: 10px;
  padding: 18px 16px 12px 16px;
  margin-bottom: 18px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.06);
}
.quiz-question {
  font-weight: bold;
  font-size: 1.1rem;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}
.quiz-index {
  background: #4361ee;
  color: #fff;
  border-radius: 50%;
  width: 28px;
  height: 28px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  margin-right: 10px;
  font-size: 1rem;
}
.quiz-options {
  margin-bottom: 10px;
  display: flex;
  gap: 12px;
}
.answer {
  color: #4361ee;
  font-weight: bold;
  margin-bottom: 4px;
}
.quiz-stats {
  margin-top: 6px;
  color: #888;
  font-size: 0.98rem;
}
.loading {
  color: #4361ee;
  text-align: center;
  margin: 20px 0;
}
.error {
  color: #e74c3c;
  text-align: center;
  margin: 20px 0;
}
.feedback-stats-section {
  margin-top: 32px;
  background: #f4f7fe;
  border-radius: 12px;
  padding: 24px 18px 18px 18px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.04);
}
.feedback-charts {
  display: flex;
  gap: 24px;
  justify-content: center;
  margin-top: 12px;
}
.feedback-chart {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.06);
  padding: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style> 