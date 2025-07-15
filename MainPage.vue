<template>
  <div class="container">
    <!-- 顶部导航栏 -->
    <header>
      <div class="logo">
        <i class="fas fa-bolt"></i>
        <span>PopQuiz</span>
      </div>
      
      <div class="nav-main">
        <button 
          v-for="(nav, index) in navItems" 
          :key="index"
          class="nav-btn" 
          :class="{ active: activeNav === nav }"
          @click="switchNav(nav)"
        >
          {{ nav }}
        </button>
      </div>
      
      <div class="role-selector">
        <button 
          v-for="(role, index) in roles" 
          :key="index"
          class="role-btn" 
          :class="{ active: activeRole === role }"
          @click="switchRole(role)"
        >
          {{ role }}
        </button>
      </div>
    </header>
    
    <!-- 主内容区 -->
    <main>
      <section class="hero">
        <h1>让课堂互动更智能、更高效</h1>
        <p>PopQuiz 利用人工智能技术，实时生成课堂测验，帮助教师了解学生掌握程度，提升课堂互动质量</p>
        
        <div class="cta-buttons">
          <button class="btn btn-primary">
            <i class="fas fa-rocket"></i> 开始新课堂
          </button>
          <button class="btn btn-outline">
            <i class="fas fa-book"></i> 查看使用教程
          </button>
        </div>
      </section>
      
      <section class="upload-section">
        <div class="section-header">
          <h2>上传演讲内容</h2>
          <p>支持文本、PPT、PDF格式文件，系统将自动生成测验题目</p>
        </div>
        
        <div 
          class="upload-area"
          :class="{ 'dragover': isDragover }"
          @dragover.prevent="onDragOver"
          @dragleave.prevent="onDragLeave"
          @drop.prevent="onDrop"
          @click="triggerFileInput"
        >
          <div class="upload-icon">
            <i :class="uploadStatus.icon" :style="{ color: uploadStatus.iconColor }"></i>
          </div>
          <div class="upload-text" :style="{ color: uploadStatus.textColor }">
            {{ uploadStatus.text }}
          </div>
          <div class="upload-hint" :style="{ color: uploadStatus.hintColor }">
            {{ uploadStatus.hint }}
          </div>
          
          <button class="btn btn-primary" @click.stop="triggerFileInput">
            <i class="fas fa-folder-open"></i> 选择文件
          </button>
          
          <input 
            type="file" 
            ref="fileInput" 
            style="display:none" 
            @change="onFileSelected"
          >
          
          <div class="file-types">
            <div v-for="(type, index) in fileTypes" :key="index" class="file-type">
              <i :class="type.icon"></i>
              <span>{{ type.name }}</span>
            </div>
          </div>
        </div>
      </section>
    </main>
    
    <!-- 页脚 -->
    <footer>
      <div class="footer-links">
        <a href="#">关于我们</a>
        <a href="#">隐私政策</a>
        <a href="#">使用条款</a>
        <a href="#">帮助中心</a>
        <a href="#">联系我们</a>
      </div>
      <p>© 2023 PopQuiz 智能课堂互动系统 | 让知识传递更高效</p>
    </footer>
  </div>
</template>

<script>
export default {
  setup() {
    // 导航相关状态
    const navItems = ['仪表盘', '内容管理', '答题统计', '讨论区', '徽章成就', '设置'];
    const activeNav = ref('仪表盘');
    
    // 角色选择状态
    const roles = ['组织者', '演讲者', '听众'];
    const activeRole = ref('组织者');
    
    // 文件上传相关状态
    const isDragover = ref(false);
    const fileInput = ref(null);
    const uploadStatus = reactive({
      icon: 'fas fa-cloud-upload-alt',
      iconColor: '#4361ee',
      text: '拖放文件到此处或点击上传',
      textColor: '#212529',
      hint: '最大文件大小：20MB',
      hintColor: '#6c757d'
    });
    
    const fileTypes = [
      { icon: 'fas fa-file-alt', name: '文本' },
      { icon: 'fas fa-file-powerpoint', name: 'PPT' },
      { icon: 'fas fa-file-pdf', name: 'PDF' }
    ];
    
    // 方法
    const switchRole = (role) => {
      activeRole.value = role;
      console.log(`切换到角色: ${role}`);
    };
    
    const switchNav = (nav) => {
      activeNav.value = nav;
    };
    
    const onDragOver = () => {
      isDragover.value = true;
    };
    
    const onDragLeave = () => {
      isDragover.value = false;
    };
    
    const onDrop = (e) => {
      isDragover.value = false;
      const files = e.dataTransfer.files;
      if (files.length) {
        handleFiles(files);
      }
    };
    
    const triggerFileInput = () => {
      fileInput.value.click();
    };
    
    const onFileSelected = (e) => {
      const files = e.target.files;
      if (files.length) {
        handleFiles(files);
      }
    };
    
    const handleFiles = (files) => {
      console.log('上传文件:', files);
      
      // 更新上传状态
      uploadStatus.icon = 'fas fa-check-circle';
      uploadStatus.iconColor = '#4caf50';
      uploadStatus.text = `已上传 ${files[0].name}`;
      uploadStatus.textColor = '#4caf50';
      uploadStatus.hint = '文件已成功上传，系统正在处理内容...';
      uploadStatus.hintColor = '#4caf50';
      
      // 处理文件逻辑
      setTimeout(() => {
        uploadStatus.hint = '处理完成！已生成测验题目';
      }, 2000);
    };
    
    return {
      navItems,
      activeNav,
      roles,
      activeRole,
      isDragover,
      fileInput,
      uploadStatus,
      fileTypes,
      switchRole,
      switchNav,
      onDragOver,
      onDragLeave,
      onDrop,
      triggerFileInput,
      onFileSelected,
      handleFiles
    };
  }
};
</script>
