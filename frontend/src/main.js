import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// src/main.js
import FontAwesomeIcon from './plugins/font-awesome'; // Import FontAwesome config

const app = createApp(App);

// Register Font Awesome globally
app.component('font-awesome-icon', FontAwesomeIcon);



app.use(store).use(router).mount('#app')
