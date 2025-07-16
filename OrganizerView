<template>
  <div class="organizer-view">
    <div class="view-header">
      <h2>组织者统计面板</h2>
      <p>查看所有演讲和参与者的综合统计信息</p>
    </div>
    
    <div class="lecture-list">
      <h3>演讲列表</h3>
      
      <div class="lecture-item">
        <div class="lecture-header">
          <h4>人工智能在课堂互动中的应用</h4>
          <span>2025-06-15</span>
        </div>
        <p>演讲者: 张教授</p>
        
        <div class="participant-stats">
          <div class="participant-stat">
            <div class="stat-value">30/35</div>
            <div class="stat-label">参与人数</div>
          </div>
          <div class="participant-stat">
            <div class="stat-value">85%</div>
            <div class="stat-label">参与率</div>
          </div>
          <div class="participant-stat">
            <div class="stat-value">72%</div>
            <div class="stat-label">平均正确率</div>
          </div>
        </div>
      </div>
      
      <div class="lecture-item">
        <div class="lecture-header">
          <h4>深度学习基础</h4>
          <span>2025-06-20</span>
        </div>
        <p>演讲者: 李教授</p>
        
        <div class="participant-stats">
          <div class="participant-stat">
            <div class="stat-value">25/28</div>
            <div class="stat-label">参与人数</div>
          </div>
          <div class="participant-stat">
            <div class="stat-value">89%</div>
            <div class="stat-label">参与率</div>
          </div>
          <div class="participant-stat">
            <div class="stat-value">68%</div>
            <div class="stat-label">平均正确率</div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="participant-performance">
      <h3>听众表现</h3>
      <div class="participant-list">
        <div class="participant-stat">
          <div class="stat-value">张三</div>
          <div>正确率: 85%</div>
          <div>排名: 1/35</div>
        </div>
        <div class="participant-stat">
          <div class="stat-value">李四</div>
          <div>正确率: 78%</div>
          <div>排名: 2/35</div>
        </div>
        <div class="participant-stat">
          <div class="stat-value">王五</div>
          <div>正确率: 72%</div>
          <div>排名: 3/35</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.organizer-view {
  background: white;
  border-radius: 16px;
  box-shadow: var(--card-shadow);
  padding: 40px;
  max-width: 1000px;
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

.lecture-list {
  margin-bottom: 30px;
}

.lecture-item {
  background: white;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 15px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.05);
  cursor: pointer;
  transition: all 0.3s ease;
}

.lecture-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.lecture-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.participant-stats {
  display: flex;
  gap: 15px;
  margin-top: 15px;
}

.participant-stat {
  flex: 1;
  text-align: center;
  padding: 10px;
  border-radius: 8px;
  background: rgba(67, 97, 238, 0.05);
}

.participant-performance {
  margin-top: 30px;
}

.participant-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 15px;
  margin-top: 15px;
}

.participant-stat {
  background: rgba(67, 97, 238, 0.05);
  border-radius: 12px;
  padding: 15px;
  text-align: center;
}
</style>
