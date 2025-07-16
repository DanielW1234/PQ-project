<template>
  <div class="container">
    <Header 
      :active-role="activeRole" 
      @role-change="changeRole"
    />
    
    <HeroSection />
    <UploadSection />
    
    <router-view />
    
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
import Header from './components/Header.vue';
import HeroSection from './components/HeroSection.vue';
import UploadSection from './components/UploadSection.vue';
import { useRouter } from 'vue-router';
import { ref, watch } from 'vue';

export default {
  components: {
    Header,
    HeroSection,
    UploadSection
  },
  setup() {
    const router = useRouter();
    const activeRole = ref('audience');
    
    const changeRole = (role) => {
      activeRole.value = role;
      router.push(`/${role}`);
    };
    
    // 监听路由变化更新角色
    watch(() => router.currentRoute.value.path, (path) => {
      const role = path.substring(1);
      if (['audience', 'speaker', 'organizer'].includes(role)) {
        activeRole.value = role;
      }
    }, { immediate: true });
    
    return {
      activeRole,
      changeRole
    };
  }
}
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
}

footer {
  text-align: center;
  padding: 40px 0;
  margin-top: 60px;
  color: var(--gray);
  border-top: 1px solid var(--border);
}

.footer-links {
  display: flex;
  gap: 25px;
  justify-content: center;
  margin-bottom: 20px;
}

.footer-links a {
  color: var(--gray);
  text-decoration: none;
  transition: all 0.3s ease;
}

.footer-links a:hover {
  color: var(--primary);
}
</style>
