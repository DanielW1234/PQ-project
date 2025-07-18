<template>
  <section class="upload-section">
    <div class="section-header">
      <h2>上传演讲内容</h2>
      <p>支持文本、PPT、PDF格式文件，系统将自动生成测验题目</p>
    </div>
    
    <div 
      class="upload-area"
      @dragover.prevent="onDragOver"
      @dragleave.prevent="onDragLeave"
      @drop.prevent="onDrop"
      @click="triggerFileInput"
      :style="{
        borderColor: isDragover ? '#4361ee' : '#dee2e6',
        backgroundColor: isDragover ? 'rgba(67, 97, 238, 0.05)' : 'rgba(67, 97, 238, 0.02)'
      }"
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
import { ref, reactive } from 'vue'

export default {
  setup() {
    const isDragover = ref(false)
    const fileInput = ref(null)
    const uploadStatus = reactive({
      icon: 'fas fa-cloud-upload-alt',
      iconColor: '#4361ee',
      text: '拖放文件到此处或点击上传',
      textColor: '#212529',
      hint: '最大文件大小：20MB',
      hintColor: '#6c757d'
    })
    
    const fileTypes = ref([
      { icon: 'fas fa-file-alt', name: '文本' },
      { icon: 'fas fa-file-powerpoint', name: 'PPT' },
      { icon: 'fas fa-file-pdf', name: 'PDF' }
    ])
    
    const onDragOver = () => {
      isDragover.value = true
    }
    
    const onDragLeave = () => {
      isDragover.value = false
    }
    
    const onDrop = (e) => {
      isDragover.value = false
      const files = e.dataTransfer.files
      if (files.length) {
        handleFiles(files)
      }
    }
    
    const triggerFileInput = () => {
      fileInput.value.click()
    }
    
    const onFileSelected = (e) => {
      const files = e.target.files
      if (files.length) {
        handleFiles(files)
      }
    }
    
    const handleFiles = (files) => {
      console.log('上传文件:', files)
      
      // 更新上传状态
      uploadStatus.icon = 'fas fa-check-circle'
      uploadStatus.iconColor = '#4caf50'
      uploadStatus.text = `已上传 ${files[0].name}`
      uploadStatus.textColor = '#4caf50'
      uploadStatus.hint = '文件已成功上传，系统正在处理内容...'
      uploadStatus.hintColor = '#4caf50'
      
      // 模拟处理过程
      setTimeout(() => {
        uploadStatus.hint = '处理完成！已生成测验题目'
      }, 2000)
    }
    
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
    }
  }
}
</script>
