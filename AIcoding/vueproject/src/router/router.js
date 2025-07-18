import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import AuthView from '@/views/AuthView.vue'
import AudienceView from '@/views/AudienceView.vue'
import SpeakerView from '@/views/SpeakerView.vue'
import OrganizerView from '@/views/OrganizerView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/auth',
    name: 'auth',
    component: AuthView
  },
  {
    path: '/audience',
    name: 'audience',
    component: AudienceView
  },
  {
    path: '/speaker',
    name: 'speaker',
    component: SpeakerView
  },
  {
    path: '/organizer',
    name: 'organizer',
    component: OrganizerView
  },
  // 添加其他功能路由
  {
    path: '/create-quiz',
    name: 'create-quiz',
    component: () => import('@/views/CreateQuizView.vue') // 懒加载
  },
  {
    path: '/upload',
    name: 'upload',
    component: () => import('@/views/UploadView.vue')
  },
  {
    path: '/analytics',
    name: 'analytics',
    component: () => import('@/views/AnalyticsView.vue')
  },
  {
    path: '/schedule',
    name: 'schedule',
    component: () => import('@/views/ScheduleView.vue')
  },
  {
    path: '/user-management',
    name: 'user-management',
    component: () => import('@/views/UserManagementView.vue')
  },
  {
    path: '/reports',
    name: 'reports',
    component: () => import('@/views/ReportsView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
