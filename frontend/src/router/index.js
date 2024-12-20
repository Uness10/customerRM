import { createRouter, createWebHistory } from 'vue-router';  // Vue 3 syntax
import Inventory from '@/views/inventory.vue';
import Dashboard from '@/views/dashboard.vue';
import Customers from '@/views/customers.vue';
import settings from '@/views/settings.vue';

import Sales from '@/views/sales.vue';
const routes = [
  { path: '/dashboard', name: 'dashboard', component: Dashboard , meta: { requiresAuth: true }  },
  {path :'/customers', name: 'customers', component: Customers,  meta: { requiresAuth: true } },
  {path :'/settings', name: 'settings', component: settings, meta: { requiresAuth: true } },
  {path :'/inventory', name: 'Inventory', component: Inventory, meta: { requiresAuth: true } },
  {path :'/sales', name: 'Sales', component: Sales, meta: { requiresAuth: true } }

  // sales and inventory
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL), // Using createWebHistory for Vue 3
  routes,
});
router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('adminId');

  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/'); // Redirect to home if not authenticated
  } else {
    next(); // Proceed as usual
  }
});

export default router;
