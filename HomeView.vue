<template>
  <div>
    <Header />
    <main>
      <HeroSection />
      <UploadSection />
    </main>
    <footer>
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
import Header from '@/components/Header.vue'
import HeroSection from '@/components/HeroSection.vue'
import UploadSection from '@/components/UploadSection.vue'

export default {
  components: {
    Header,
    HeroSection,
    UploadSection
  }
}
</script>
