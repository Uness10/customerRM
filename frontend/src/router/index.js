import { createRouter, createWebHistory } from 'vue-router';  // Vue 3 syntax
import Home from '@/views/home.vue';
import Dashboard from '@/views/dashboard.vue';

const routes = [
  { path: '/', name: 'home', component: Home },
  { path: '/dashboard', name: 'dashboard', component: Dashboard },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL), // Using createWebHistory for Vue 3
  routes,
});

export default router;
