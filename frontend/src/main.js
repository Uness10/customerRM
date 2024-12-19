import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// src/main.js
import './index.css'; // Ensure this is the path to your CSS file

import 'font-awesome/css/font-awesome.min.css';

const app = createApp(App);




app.use(store).use(router).mount('#app')
