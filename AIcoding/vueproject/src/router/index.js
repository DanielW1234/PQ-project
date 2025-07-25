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
import axios from 'axios'
import AudienceLectureDetailView from '@/views/AudienceLectureDetailView.vue'
import OrganizerLectureDetailView from '@/views/OrganizerLectureDetailView.vue'

// 用户注册
export async function register(user) {
  // user: { username, password, role }
  return axios.post('/user/register', user)
}

// 用户登录
export async function login({ username, password }) {
  return axios.post('/user/login', { username, password })
}

// 讲座相关API
export async function getLectures() {
  return axios.get('/lecture')
}
export async function getLectureById(id) {
  return axios.get(`/lecture/${id}`)
}
export async function getLectureStats(id) {
  return axios.get(`/lecture/stats/${id}`)
}

// 题目相关API
export async function getQuizzes() {
  return axios.get('/quiz')
}
export async function getQuizById(id) {
  return axios.get(`/quiz/${id}`)
}
export async function getQuizStats(id) {
  return axios.get(`/quiz/stats/${id}`)
}

// 选项相关API
export async function getQuizOptions() {
  return axios.get('/quizOption')
}
export async function getQuizOptionById(id) {
  return axios.get(`/quizOption/${id}`)
}

// 答案相关API
export async function getAnswers() {
  return axios.get('/answer')
}
export async function getAnswerById(id) {
  return axios.get(`/answer/${id}`)
}
export async function addAnswer(answer) {
  return axios.post('/answer', answer)
}

// 讨论相关API
export async function getDiscussions() {
  return axios.get('/discussion')
}
export async function getDiscussionById(id) {
  return axios.get(`/discussion/${id}`)
}
export async function addDiscussion(discussion) {
  return axios.post('/discussion', discussion)
}

// 反馈相关API
export async function getFeedbacks() {
  return axios.get('/feedback')
}
export async function getFeedbackById(id) {
  return axios.get(`/feedback/${id}`)
}
export async function addFeedback(feedback) {
  return axios.post('/feedback', feedback)
}

const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/auth', name: 'auth', component: AuthView },
  { path: '/organizer', name: 'organizer', component: OrganizerView },
  { path: '/speaker', name: 'speaker', component: SpeakerView },
  { path: '/audience', name: 'audience', component: AudienceView },
  { path: '/lecture/:id', name: 'lecture-detail', component: LectureDetailView },
  { path: '/lecture/audience/:id', name: 'AudienceLectureDetail', component: AudienceLectureDetailView },
  { path: '/lecture/organizer/:id', name: 'OrganizerLectureDetail', component: OrganizerLectureDetailView },
  { path: '/lecture/speaker/:id', name: 'SpeakerLectureDetail', component: OrganizerLectureDetailView },
  { path: '/user', name: 'user-center', component: UserCenterView },
  { path: '/quiz/:id', name: 'quiz-detail', component: QuizDetailView },
  { path: '/:pathMatch(.*)*', name: 'notfound', component: NotFound }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局路由守卫
router.beforeEach((to, from, next) => {
  const user = JSON.parse(localStorage.getItem('user') || '{}')
  const role = user.role
  // 允许未登录访问首页和登录/注册
  if (!role && (to.name === 'home' || to.name === 'auth' || to.name === 'notfound')) {
    return next()
  }
  // 已登录用户只能访问自己角色页面、首页和讲座详情页
  if (role === 'audience' && !['audience', 'home', 'lecture-detail', 'AudienceLectureDetail'].includes(to.name)) {
    return next({ name: 'audience' })
  }
  if (role === 'speaker' && !['speaker', 'home', 'lecture-detail', 'SpeakerLectureDetail', 'OrganizerLectureDetail'].includes(to.name)) {
    return next({ name: 'speaker' })
  }
  if (role === 'organizer' && !['organizer', 'home', 'lecture-detail', 'OrganizerLectureDetail', 'SpeakerLectureDetail'].includes(to.name)) {
    return next({ name: 'organizer' })
  }
  // 其他情况正常放行
  next()
})

export default router 