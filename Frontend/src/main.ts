import { createApp } from 'vue';
import type { Component } from 'vue';
import App from './App.vue';
import router from './router';  // Importer le routeur

const app: ReturnType<typeof createApp> = createApp(App as Component);
app.use(router);  // Utiliser Vue Router

app.mount('#app');
