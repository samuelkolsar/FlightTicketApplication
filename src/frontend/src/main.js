import { createApp } from 'vue';
import App from './App.vue';
import router from './router/index.js'; // Ensure router is imported if using Vue Router

const app = createApp(App);

app.use(router); // If using Vue Router
app.mount('#app');
