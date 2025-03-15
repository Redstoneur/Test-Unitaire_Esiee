import {
    createRouter,
    createWebHistory,
    NavigationGuardNext,
    RouteLocationNormalized,
    Router,
    RouteRecordRaw
} from 'vue-router';
import {Component} from "vue";

// Components
import Credentials from './components/Credentials.vue';
import Home from './components/Home.vue';
import AddObjectScreen from './components/addObjetScreen.vue';

// Functions
import {BooleanVerifyToken} from "./Function/ApiRequest";


const requireAuth = async (
    to: RouteLocationNormalized,
    from: RouteLocationNormalized,
    next: NavigationGuardNext
) => {
    const token = localStorage.getItem('authToken');
    if (!token) {
        next('/login');
    } else {
        const isValid = await BooleanVerifyToken(token);
        if (isValid) {
            next();
        } else {
            next('/login');
        }
    }
};

const routes: RouteRecordRaw[] = [
    {
        path: '/login',
        name: 'login',
        component: Credentials as unknown as Component,
    },
    {
        path: '/',
        alias: '/home',
        name: 'home',
        component: Home as unknown as Component,
        beforeEnter: requireAuth,
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