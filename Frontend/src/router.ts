import { createRouter, createWebHistory } from 'vue-router';
import Credentions from './components/Credentials.vue';
import AddObjectScreen from './components/addObjetScreen.vue';

const routes = [
    {
        path: '/', // Page d'accueil
        name: 'home',
        component: Credentions, // Composant pour l'authentification
    },
    {
        path: '/add-object', // Page pour ajouter un objet
        name: 'add-object',
        component: AddObjectScreen, // Composant pour ajouter un objet
    },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;
