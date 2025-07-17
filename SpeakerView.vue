<template>
  <div>
    <Header />
    <main>
      <div class="role-view">
        <div class="role-header">
          <button class="logout-btn" @click="logout">
            <i class="fas fa-sign-out-alt"></i> 退出登录
          </button>
          
          <div>
            <h2 class="welcome-title">欢迎回来，{{ username }}！</h2>
            <span class="role-tag">演讲者视图</span>
          </div>
        </div>
        
        <div class="function-cards">
          <div class="function-card">
            <i class="fas fa-plus-circle"></i>
            <h3>创建测验</h3>
            <p>为您的课堂制作新测验</p>
          </div>
          <div class="function-card">
            <i class="fas fa-file-upload"></i>
            <h3>上传内容</h3>
            <p>上传PPT或PDF生成测验</p>
          </div>
          <div class="function-card">
            <i class="fas fa-chart-pie"></i>
            <h3>分析结果</h3>
            <p>查看学生答题统计数据</p>
          </div>
        </div>
      </div>
    </main
