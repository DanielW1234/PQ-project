import { createRouter, createWebHistory } from 'vue-router';
import AudienceView from './components/AudienceView.vue';
import SpeakerView from './components/SpeakerView.vue';
import OrganizerView from './components/OrganizerView.vue';

const routes = [
  { path: '/', redirect: '/audience' },
  { path: '/audience', component: AudienceView },
  { path: '/speaker', component: SpeakerView },
  { path: '/organizer', component: OrganizerView }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
