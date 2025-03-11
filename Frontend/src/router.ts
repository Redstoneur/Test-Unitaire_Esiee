import {
    createRouter,
    createWebHistory,
    NavigationGuardNext,
    RouteLocationNormalized,
    Router,
    RouteRecordRaw
} from 'vue-router';
import Credentions from './components/Credentials.vue';
import AddObjectScreen from './components/addObjetScreen.vue';
import {BooleanVerifyToken} from "./Function/ApiRequest";
import {Component} from "vue";

const requireAuth = async (to: RouteLocationNormalized, from: RouteLocationNormalized,
                           next: NavigationGuardNext) => {
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

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        component: Credentions as unknown as Component,
    },
    {
        path: '/add-object',
        name: 'add-object',
        component: AddObjectScreen as unknown as Component,
        beforeEnter: requireAuth,
    },
];

const router: Router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;
