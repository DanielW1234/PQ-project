import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import AuthView from '@/views/AuthView.vue'
import OrganizerView from '@/views/OrganizerView.vue'
import SpeakerView from '@/views/SpeakerView.vue'
import AudienceView from '@/views/AudienceView.vue'
import LectureDetailView from '@/views/LectureDetailView.vue'
import UserCenterView from '@/views/UserCenterView.vue'
import QuizDetailView from '@/views/QuizDetailView.vue'
import NotFound from '@/views/NotFound.vue'

const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/auth', name: 'auth', component: AuthView },
  { path: '/organizer', name: 'organizer', component: OrganizerView },
  { path: '/speaker', name: 'speaker', component: SpeakerView },
  { path: '/audience', name: 'audience', component: AudienceView },
  { path: '/lecture/:id', name: 'lecture-detail', component: LectureDetailView },
  { path: '/user', name: 'user-center', component: UserCenterView },
  { path: '/quiz/:id', name: 'quiz-detail', component: QuizDetailView },
  { path: '/:pathMatch(.*)*', name: 'notfound', component: NotFound }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 