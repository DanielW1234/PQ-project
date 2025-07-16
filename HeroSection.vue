<template>
  <section class="hero">
    <h1>让课堂互动更智能、更高效</h1>
    <p>PopQuiz 利用人工智能技术，实时生成课堂测验，帮助教师了解学生掌握程度，提升课堂互动质量</p>
    
    <div class="cta-buttons">
      <button class="btn btn-primary" @click="startNewLecture">
        <i>🚀</i> 开始新课堂
      </button>
      <button class="btn btn-outline">
        <i>📚</i> 查看使用教程
      </button>
    </div>
  </section>
</template>

<script>
export default {
  methods: {
    startNewLecture() {
      alert("新课堂已创建！");
    }
  }
}
</script>

<style scoped>
.hero {
  text-align: center;
  padding: 60px 20px;
  max-width: 800px;
  margin: 0 auto;
}

.hero h1 {
  font-size: 2.8rem;
  margin-bottom: 20px;
  color: var(--dark);
  line-height: 1.2;
}

.hero p {
  font-size: 1.2rem;
  color: var(--gray);
  margin-bottom: 40px;
  line-height: 1.6;
}

.cta-buttons {
  display: flex;
  gap: 15px;
  justify-content: center;
  margin-bottom: 50px;
}

.btn {
  padding: 14px 30px;
  border-radius: 8px;
  font-size: 1.1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  display: inline-flex;
  align-items: center;
  gap: 10px;
}

.btn-primary {
  background: var(--primary);
  color: white;
  border: none;
  box-shadow: 0 4px 12px rgba(67, 97, 238, 0.3);
}

.btn-primary:hover {
  background: var(--secondary);
  transform: translateY(-2px);
}

.btn-outline {
  background: transparent;
  border: 2px solid var(--primary);
  color: var(--primary);
}

.btn-outline:hover {
  background: rgba(67, 97, 238, 0.1);
  transform: translateY(-2px);
}
</style>
