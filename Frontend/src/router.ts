import {createRouter, createWebHistory, Router, RouteRecordRaw} from 'vue-router';
import Credentions from './components/Credentials.vue';
import AddObjectScreen from './components/addObjetScreen.vue';
import {BooleanVerifyToken} from "./Function/ApiRequest";

const requireAuth = async (to: any, from: any, next: any) => {
    const token = localStorage.getItem('authToken');
    if (!token) {
        next('/');
    } else {
        const isValid = await BooleanVerifyToken(token);
        if (isValid) {
            next();
        } else {
            next('/');
        }
    }
};

const routes: Array<RouteRecordRaw> = [
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

const router: Router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;
