<template>
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
</template>

<script>
import { ref, reactive } from 'vue';

export default {
  setup() {
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
    
    const fileTypes = ref([
      { icon: 'fas fa-file-alt', name: '文本' },
      { icon: 'fas fa-file-powerpoint', name: 'PPT' },
      { icon: 'fas fa-file-pdf', name: 'PDF' }
    ]);
    
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
      isDragover,
      fileInput,
      uploadStatus,
      fileTypes,
      onDragOver,
      onDragLeave,
      onDrop,
      triggerFileInput,
      onFileSelected
    };
  }
}
</script>

<style scoped>
.upload-section {
  background: white;
  border-radius: 16px;
  box-shadow: var(--card-shadow);
  padding: 40px;
  max-width: 800px;
  margin: 0 auto;
}

.section-header {
  text-align: center;
  margin-bottom: 30px;
}

.section-header h2 {
  font-size: 1.8rem;
  color: var(--dark);
  margin-bottom: 10px;
}

.section-header p {
  color: var(--gray);
  font-size: 1.1rem;
}

.upload-area {
  border: 2px dashed var(--border);
  border-radius: 12px;
  padding: 50px 20px;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s ease;
  background: rgba(67, 97, 238, 0.02);
  position: relative;
  overflow: hidden;
}

.upload-area:hover {
  border-color: var(--primary);
  background: rgba(67, 97, 238, 0.05);
}

.upload-area.dragover {
  border-color: var(--primary);
  background: rgba(67, 97, 238, 0.1);
}

.upload-icon {
  font-size: 3.5rem;
  margin-bottom: 20px;
}

.upload-text {
  font-size: 1.3rem;
  margin-bottom: 10px;
  font-weight: 500;
}

.upload-hint {
  font-size: 1rem;
  margin-bottom: 20px;
}

.file-types {
  display: flex;
  gap: 20px;
  justify-content: center;
  margin-top: 30px;
}

.file-type {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  border-radius: 10px;
  background: rgba(67, 97, 238, 0.05);
  width: 120px;
  transition: all 0.3s ease;
}

.file-type:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 15px rgba(0,0,0,0.1);
}

.file-type i {
  font-size: 2.5rem;
  color: var(--primary);
  margin-bottom: 15px;
}

.file-type span {
  font-weight: 500;
  color: var(--dark);
}
</style>
