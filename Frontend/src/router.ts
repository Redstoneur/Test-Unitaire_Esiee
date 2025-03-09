import {createRouter, createWebHistory} from 'vue-router';
import Credentions from './components/Credentials.vue';
import AddObjectScreen from './components/addObjetScreen.vue';

const requireAuth = (to: any, from: any, next: any) => {
    const token = localStorage.getItem('authToken');
    if (!token) {
        next('/');
    } else {
        next();
    }
};

const routes = [
    {
        path: '/',
        name: 'home',
        component: Credentions,
    },
    {
        path: '/add-object',
        name: 'add-object',
        component: AddObjectScreen,
        beforeEnter: requireAuth,
    },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;
