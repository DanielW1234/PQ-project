<template>
  <div class="speaker-view">
    <div class="view-header">
      <h2>演讲者统计面板</h2>
      <p>查看听众答题情况和统计信息</p>
    </div>
    
    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-value">35</div>
        <div class="stat-label">听众人数</div>
      </div>
      <div class="stat-card">
        <div class="stat-value">85%</div>
        <div class="stat-label">答题参与率</div>
      </div>
      <div class="stat-card">
        <div class="stat-value">72%</div>
        <div class="stat-label">平均正确率</div>
      </div>
    </div>
    
    <div class="question-stats">
      <h3>问题统计</h3>
      
      <div class="question-stat">
        <div class="quiz-question">1. PopQuiz的主要功能是什么？</div>
        <div>答题率: 85%</div>
        <div class="stat-bar">
          <div class="stat-bar-fill" style="width: 85%"></div>
        </div>
        <div>正确率: 72%</div>
        <div class="stat-bar">
          <div class="stat-bar-fill" style="width: 72%"></div>
        </div>
      </div>
      
      <div class="question-stat">
        <div class="quiz-question">2. AI生成测验题目的优势是什么？</div>
        <div>答题率: 78%</div>
        <div class="stat-bar">
          <div class="stat-bar-fill" style="width: 78%"></div>
        </div>
        <div>正确率: 65%</div>
        <div class="stat-bar">
          <div class="stat-bar-fill" style="width: 65%"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.speaker-view {
  background: white;
  border-radius: 16px;
  box-shadow: var(--card-shadow);
  padding: 40px;
  max-width: 800px;
  margin: 20px auto;
}

.view-header {
  text-align: center;
  margin-bottom: 30px;
}

.view-header h2 {
  font-size: 1.8rem;
  color: var(--dark);
  margin-bottom: 10px;
}

.view-header p {
  color: var(--gray);
  font-size: 1.1rem;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  background: rgba(67, 97, 238, 0.05);
  border-radius: 12px;
  padding: 20px;
  text-align: center;
}

.stat-value {
  font-size: 2rem;
  font-weight: bold;
  color: var(--primary);
  margin-bottom: 10px;
}

.stat-label {
  color: var(--gray);
}

.question-stats {
  margin-top: 30px;
}

.question-stat {
  background: white;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 15px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.05);
}

.stat-bar {
  height: 10px;
  background: var(--border);
  border-radius: 5px;
  margin-top: 10px;
  overflow: hidden;
}

.stat-bar-fill {
  height: 100%;
  background: var(--primary);
  border-radius: 5px;
}
</style>
