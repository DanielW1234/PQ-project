<template>
  <section class="hero">
    <h1>让课堂互动更智能、更高效</h1>
    <p>PopQuiz 利用人工智能技术，实时生成课堂测验，帮助教师了解学生掌握程度，提升课堂互动质量</p>
    
    <div class="cta-buttons">
      <button class="btn btn-primary" @click="startNewLecture">
        <i class="fas fa-rocket"></i> 开始新课堂
      </button>
      <button class="btn btn-outline">
        <i class="fas fa-book"></i> 查看使用教程
      </button>
    </div>
  </section>
</template>

<script>
export default {
  methods: {
    startNewLecture() {
      alert("新课堂已创建！")
    }
  }
}
</script>
