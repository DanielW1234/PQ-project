<template>
  <div class="role-page-container">
    <header class="header-bar">
      <div class="brand">PopQuiz</div>
      <nav class="nav-links">
        <router-link to="/" class="nav-link">首页</router-link>
        <router-link to="/speaker" class="nav-link">演讲者</router-link>
      </nav>
    </header>
    <div class="logout-bar">
      <button class="logout-btn" @click="logout">
        <i class="fas fa-sign-out-alt"></i> 退出登录
      </button>
    </div>
    <main class="main-content">
      <div class="welcome-info">
        <h2 class="welcome-title">欢迎回来，{{ username }}！</h2>
        <span class="role-tag">演讲者视图</span>
      </div>
      <div class="user-info">
        <div><b>用户名：</b>{{ username }}</div>
      </div>
      <div class="lecture-list">
        <h3>我主讲的讲座</h3>
        <div v-if="loading" style="color:#4361ee;">讲座加载中...</div>
        <div v-if="error" style="color:red;">{{ error }}</div>
        <ul>
          <li v-for="lecture in lectures" :key="lecture.id" class="lecture-item">
            <div class="lecture-title">
              讲座名称：
              <router-link :to="{ name: 'lecture-detail', params: { id: lecture.id } }" class="lecture-link">
                {{ lecture.title }}
              </router-link>
            </div>
            <div class="lecture-desc">
              讲座描述：{{ lecture.description }}
            </div>
          </li>
        </ul>
      </div>
      <!-- 上传讲座内容区 -->
      <div class="upload-section">
        <h3 class="upload-title">上传演讲内容</h3>
        <div class="upload-area" @dragover.prevent @drop.prevent="handleDrop" @click="triggerFileInput">
          <div class="upload-icon">
            <svg width="48" height="48" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
              <rect width="48" height="48" rx="12" fill="#e3eaff"/>
              <path d="M24 34V18M24 18L18 24M24 18L30 24" stroke="#4361ee" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
          </div>
          <div class="upload-tip">拖放文件到此处或点击上传</div>
          <div class="upload-size">最大文件大小：20MB</div>
          <input ref="fileInput" type="file" class="file-input" @change="handleFileChange" :accept="acceptTypes" hidden />
        </div>
        <div class="upload-btns">
          <button class="upload-btn" @click="triggerFileInput">
            <i class="fas fa-folder-open"></i> 选择文件
          </button>
        </div>
        <div v-if="selectedFile" class="selected-file">
          已选择文件：{{ selectedFile.name }}
        </div>
        <div v-if="fileUrl" class="file-url">文件已上传，点击下方“开始讲座”创建讲座。</div>
        <form v-if="fileUrl" class="start-lecture-form" @submit.prevent="startLecture">
          <div class="form-group">
            <label class="input-label">讲座标题</label>
            <input v-model="lectureTitle" class="form-control" placeholder="请输入讲座标题" required />
          </div>
          <div class="form-group">
            <label class="input-label">讲座描述</label>
            <input v-model="lectureDesc" class="form-control" placeholder="请输入讲座描述" />
          </div>
          <button class="upload-btn upload-btn-main" type="submit">开始讲座</button>
        </form>
        <div class="file-types">
          <div class="file-type">
            <i class="fas fa-file-alt"></i> 文本
          </div>
          <div class="file-type">
            <i class="fas fa-file-powerpoint"></i> PPT
          </div>
          <div class="file-type">
            <i class="fas fa-file-pdf"></i> PDF
          </div>
        </div>
        <div v-if="uploadMsg" class="upload-msg">{{ uploadMsg }}</div>
      </div>
    </main>
    <footer class="footer-bar">
      <div class="footer-links">
        <a href="#">关于我们</a>
        <a href="#">隐私政策</a>
        <a href="#">使用条款</a>
        <a href="#">帮助中心</a>
        <a href="#">联系我们</a>
      </div>
      <p>© 2025 PopQuiz 智能课堂互动系统 | 让知识传递更高效</p>
    </footer>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getLectures, getLectureStats } from '@/router/index.js'
import axios from 'axios'

export default {
  setup() {
    const user = ref(JSON.parse(localStorage.getItem('user') || '{}'))
    const username = ref(user.value.username || '')
    const lectures = ref([])
    const loading = ref(false)
    const error = ref('')
    const router = useRouter()

    // 上传相关
    const fileInput = ref(null)
    const selectedFile = ref(null)
    const fileUrl = ref('')
    const acceptTypes = '.txt,.pdf,.ppt,.pptx'
    const uploadMsg = ref('')

    // 讲座表单
    const lectureTitle = ref('')
    const lectureDesc = ref('')

    const triggerFileInput = () => {
      fileInput.value && fileInput.value.click()
    }
    const handleFileChange = async (e) => {
      const file = e.target.files[0]
      if (file) {
        selectedFile.value = file
        uploadMsg.value = ''
        // 上传到后端，获取fileUrl
        const formData = new FormData()
        formData.append('file', file)
        try {
          uploadMsg.value = '上传中...'
          const res = await axios.post('/files/upload', formData, { headers: { 'Content-Type': 'multipart/form-data' } })
          fileUrl.value = res.data.data
          uploadMsg.value = '上传成功！'
        } catch (e) {
          uploadMsg.value = '上传失败，请重试'
        }
      }
    }
    const handleDrop = async (e) => {
      const file = e.dataTransfer.files[0]
      if (file) {
        selectedFile.value = file
        uploadMsg.value = ''
        // 上传到后端，获取fileUrl
        const formData = new FormData()
        formData.append('file', file)
        try {
          uploadMsg.value = '上传中...'
          const res = await axios.post('/files/upload', formData, { headers: { 'Content-Type': 'multipart/form-data' } })
          fileUrl.value = res.data.data
          uploadMsg.value = '上传成功！'
        } catch (e) {
          uploadMsg.value = '上传失败，请重试'
        }
      }
    }

    // 开始讲座
    const startLecture = async () => {
      if (!lectureTitle.value || !fileUrl.value) return
      try {
        const res = await axios.post('/lecture/upload', null, {
          params: {
            fileUrl: fileUrl.value,
            title: lectureTitle.value,
            description: lectureDesc.value,
            speakerId: user.value.id,
            organizerId: user.value.id
          }
        })
        uploadMsg.value = '题目正在生成中……'
        lectureTitle.value = ''
        lectureDesc.value = ''
        fileUrl.value = ''
        selectedFile.value = null
        // 延迟3秒再刷新讲座列表，确保AI题目生成
        setTimeout(async () => {
          await loadLectures()
          uploadMsg.value = '讲座和题目已全部生成！'
        }, 3000)
      } catch (e) {
        uploadMsg.value = '讲座创建失败，请重试'
      }
    }

    // 讲座加载
    const loadLectures = async () => {
      loading.value = true
      error.value = ''
      try {
        const res = await getLectures()
        const lectureArr = await Promise.all(res.data.data.map(async lec => {
          const detailRes = await axios.get(`/lecture/${lec.id}/detail`)
          const detail = detailRes.data.data
          // 只保留讲座基本信息和题目统计
          return {
            ...detail.lecture,
            quizzes: detail.quizzes.map(q => ({
              questionText: q.quiz.questionText,
              answerCount: q.answerCount,
              correctRate: q.correctRate
            }))
          }
        }))
        lectures.value = lectureArr
      } catch (e) {
        error.value = '讲座加载失败，请重试'
      } finally {
        loading.value = false
      }
    }
    onMounted(loadLectures)
    const logout = () => {
      localStorage.removeItem('user')
      router.push({ name: 'auth' })
    }
    return {
      username,
      lectures,
      loading,
      error,
      logout,
      // 上传相关
      fileInput,
      selectedFile,
      fileUrl,
      acceptTypes,
      uploadMsg,
      triggerFileInput,
      handleFileChange,
      handleDrop,
      // 讲座表单
      lectureTitle,
      lectureDesc,
      startLecture
    }
  }
}
</script>

<style scoped>
.role-page-container {
  min-height: 100vh;
  background: #fff;
  display: flex;
  flex-direction: column;
}
.header-bar {
  background: #4361ee;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  height: 56px;
}
.brand {
  font-size: 22px;
  font-weight: bold;
  letter-spacing: 2px;
}
.nav-links {
  display: flex;
  gap: 24px;
}
.nav-link {
  color: #fff;
  text-decoration: none;
  font-size: 16px;
  transition: color 0.2s;
}
.nav-link:hover {
  color: #ffd700;
}
.logout-bar {
  background: #f6f8fa;
  padding: 16px 32px 0 32px;
}
.logout-btn {
  background: #fff;
  color: #4361ee;
  border: 1.5px solid #4361ee;
  border-radius: 8px;
  padding: 8px 24px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
}
.logout-btn:hover {
  background: #4361ee;
  color: #fff;
}
.main-content {
  max-width: 800px;
  margin: 32px auto 0 auto;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 16px rgba(67,97,238,0.08);
  padding: 32px 24px 24px 24px;
}
.welcome-info {
  margin-bottom: 16px;
}
.welcome-title {
  font-size: 1.7rem;
  font-weight: bold;
  color: #222;
  margin-bottom: 4px;
}
.role-tag {
  color: #4361ee;
  font-size: 1.1rem;
  font-weight: 500;
}
.user-info {
  margin-bottom: 24px;
}
.lecture-list {
  margin-top: 24px;
}
.lecture-item {
  background: #f8f9fa;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
  display: flex;
  flex-direction: column;
}
.lecture-title {
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 8px;
}
.lecture-desc {
  font-size: 14px;
  color: #555;
  margin-bottom: 8px;
}
.lecture-link {
  color: #4361ee;
  text-decoration: none;
  transition: color 0.2s;
}
.lecture-link:hover {
  color: #ffd700;
}
.lecture-stats span {
  margin-right: 24px;
}
.upload-section {
  margin-top: 40px;
  background: #fafbff;
  border-radius: 12px;
  padding: 32px 24px 24px 24px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.04);
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}
.upload-title {
  font-size: 1.3rem;
  font-weight: bold;
  color: #222;
  margin-bottom: 10px;
}
.upload-area {
  width: 100%;
  min-height: 140px;
  border: 2px dashed #e0e0e0;
  border-radius: 12px;
  background: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  margin-bottom: 18px;
  transition: border-color 0.2s;
}
.upload-area:hover {
  border-color: #4361ee;
}
.upload-icon {
  margin-bottom: 12px;
}
.upload-tip {
  color: #222;
  font-size: 1.1rem;
  margin-bottom: 6px;
}
.upload-size {
  color: #aaa;
  font-size: 0.95rem;
}
.file-input {
  display: none;
}
.upload-btns {
  display: flex;
  gap: 16px;
  margin-bottom: 10px;
}
.upload-btn {
  background: #4361ee;
  color: #fff;
  border: none;
  border-radius: 8px;
  padding: 10px 28px;
  font-size: 1.05rem;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  box-shadow: 0 2px 8px rgba(67,97,238,0.08);
  transition: background 0.2s;
}
.upload-btn-main {
  background: #233a7c;
}
.upload-btn:disabled {
  background: #bfc8e6;
  color: #fff;
  cursor: not-allowed;
}
.file-types {
  display: flex;
  gap: 16px;
  margin-top: 12px;
  margin-bottom: 8px;
}
.file-type {
  background: #f3f4f8;
  color: #888;
  border-radius: 6px;
  padding: 8px 18px;
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 1rem;
}
.selected-file {
  margin-top: 10px;
  color: #4361ee;
  font-size: 1rem;
}
.file-url {
  margin-top: 10px;
  color: #4361ee;
  font-size: 1rem;
  text-align: center;
}
.start-lecture-form {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 15px;
}
.form-group {
  display: flex;
  flex-direction: column;
}
.input-label {
  font-size: 1rem;
  color: #555;
  margin-bottom: 5px;
}
.form-control {
  padding: 10px 15px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.2s;
}
.form-control:focus {
  border-color: #4361ee;
  outline: none;
}
.upload-msg {
  margin-top: 8px;
  color: #4361ee;
  font-size: 1rem;
}
.footer-bar {
  margin-top: 40px;
  background: #f6f8fa;
  padding: 24px 0 8px 0;
  text-align: center;
}
.footer-links {
  display: flex;
  justify-content: center;
  gap: 24px;
  margin-bottom: 8px;
}
.footer-links a {
  color: #4361ee;
  text-decoration: none;
  font-size: 15px;
  transition: color 0.2s;
}
.footer-links a:hover {
  color: #ffd700;
}
</style> 