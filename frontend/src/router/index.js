import { createRouter, createWebHistory } from 'vue-router';  // Vue 3 syntax
import Home from '@/views/home.vue';
import Dashboard from '@/views/dashboard.vue';
import Customers from '@/views/customers.vue';
import settings from '@/views/settings.vue';
const routes = [
  { path: '/', name: 'home', component: Home },
  { path: '/dashboard', name: 'dashboard', component: Dashboard },
  {path :'/customers', name: 'customers', component: Customers},
  {path :'/settings', name: 'settings', component: settings}
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL), // Using createWebHistory for Vue 3
  routes,
});

export default router;
