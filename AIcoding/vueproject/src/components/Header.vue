<template>
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
        class="role-btn" 
        :class="{ active: role === 'organizer' }"
        @click="switchRole('organizer')"
      >
        组织者
      </button>
      <button 
        class="role-btn" 
        :class="{ active: role === 'speaker' }"
        @click="switchRole('speaker')"
      >
        演讲者
      </button>
      <button 
        class="role-btn" 
        :class="{ active: role === 'audience' }"
        @click="switchRole('audience')"
      >
        听众
      </button>
    </div>
  </header>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter()
    const role = ref('organizer')
    const activeNav = ref('仪表盘')
    const navItems = ref(['仪表盘', '内容管理', '答题统计', '讨论区', '徽章成就', '设置'])
    
    const switchRole = (selectedRole) => {
      role.value = selectedRole
      switch(selectedRole) {
        case 'audience':
          router.push({ name: 'audience' })
          break
        case 'speaker':
          router.push({ name: 'speaker' })
          break
        case 'organizer':
          router.push({ name: 'organizer' })
          break
      }
    }
    
    const switchNav = (nav) => {
      activeNav.value = nav
    }
    
    return {
      role,
      activeNav,
      navItems,
      switchRole,
      switchNav
    }
  }
}
</script>
