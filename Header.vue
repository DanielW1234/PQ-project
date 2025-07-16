<template>
  <header>
    <div class="logo">
      <i>⚡</i>
      <span>PopQuiz</span>
    </div>
    
    <div class="nav-main">
      <button class="nav-btn active">仪表盘</button>
      <button class="nav-btn">内容管理</button>
      <button class="nav-btn">答题统计</button>
      <button class="nav-btn">讨论区</button>
      <button class="nav-btn">徽章成就</button>
      <button class="nav-btn">设置</button>
    </div>
    
    <div class="role-selector">
      <button 
        class="role-btn" 
        :class="{ active: activeRole === 'audience' }"
        @click="$emit('role-change', 'audience')"
      >
        听众
      </button>
      <button 
        class="role-btn" 
        :class="{ active: activeRole === 'speaker' }"
        @click="$emit('role-change', 'speaker')"
      >
        演讲者
      </button>
      <button 
        class="role-btn" 
        :class="{ active: activeRole === 'organizer' }"
        @click="$emit('role-change', 'organizer')"
      >
        组织者
      </button>
    </div>
  </header>
</template>

<script>
export default {
  props: {
    activeRole: {
      type: String,
      default: 'audience'
    }
  },
  emits: ['role-change']
}
</script>

<style scoped>
header {
  background: white;
  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
  border-radius: 12px;
  padding: 15px 25px;
  margin-bottom: 25px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--primary);
}

.logo i {
  color: var(--warning);
}

.nav-main {
  display: flex;
  gap: 5px;
}

.nav-btn {
  padding: 10px 20px;
  border-radius: 8px;
  background: transparent;
  border: none;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.3s ease;
  font-weight: 500;
  color: var(--gray);
}

.nav-btn:hover, .nav-btn.active {
  background: rgba(67, 97, 238, 0.1);
  color: var(--primary);
}

.role-selector {
  display: flex;
  gap: 10px;
  background: rgba(67, 97, 238, 0.05);
  padding: 8px;
  border-radius: 50px;
}

.role-btn {
  padding: 8px 20px;
  border-radius: 30px;
  background: transparent;
  border: none;
  font-size: 0.95rem;
  cursor: pointer;
  transition: all 0.3s ease;
}

.role-btn.active {
  background: var(--primary);
  color: white;
  box-shadow: 0 4px 8px rgba(67, 97, 238, 0.3);
}
</style>
