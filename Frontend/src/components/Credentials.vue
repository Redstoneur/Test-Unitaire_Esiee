<script setup lang="ts">
import {ref, onMounted} from 'vue';
import ObjetScreen from './ObjetScreen.vue';
import {useRouter} from 'vue-router';
import ApiRequest from "../Class/ApiRequest";

// Déclaration des variables réactives
const isLoginMode = ref(true);
const pseudo = ref('');
const mdp = ref('');
const mail = ref('');
const nom = ref('');
const prenom = ref('');
const errorMessage = ref('');
const isAuthenticated = ref(false);
const router = useRouter();
const searchText = ref('');
const searchCategorie = ref('');
const searchUser = ref('');
const filteredObjets = ref([]);

// Changer le mode (connexion/inscription)
const toggleMode = () => {
  isLoginMode.value = !isLoginMode.value;
  resetForm(); // Réinitialiser les champs quand on change de mode
};

// Réinitialiser le formulaire
const resetForm = () => {
  pseudo.value = '';
  mdp.value = '';
  mail.value = '';
  nom.value = '';
  prenom.value = '';
  errorMessage.value = '';
};

// Inscription de l'utilisateur
const register = async () => {
  try {
    const response = await ApiRequest.Register(
        pseudo.value,
        mdp.value,
        mail.value,
        nom.value,
        prenom.value
    );

    if (response instanceof Error || !response.ok)
      throw new Error('Erreur lors de l\'inscription');

    const data = await response.json();

    // Sauvegarde du token et connexion immédiate
    const token = data.token;
    const isValid = await ApiRequest.BooleanVerifyToken(token);
    if (isValid) {
      localStorage.setItem('authToken', token);
      isAuthenticated.value = true;
    }

    // Réinitialiser le formulaire
    resetForm();

    // Redirection vers la page d'accueil
    await router.push('/');
  } catch (error: any) {
    errorMessage.value = (error as Error).message;
  }
};

// Connexion de l'utilisateur
const login = async () => {
  try {
    const response = await ApiRequest.Login(mail.value, mdp.value);

    if (response instanceof Error || !response.ok)
      throw new Error('Échec de la connexion');

    const data = await response.json();

    // Sauvegarder le jeton et connexion immédiate
    const token = data.token;
    const isValid = await ApiRequest.BooleanVerifyToken(token);
    if (isValid) {
      localStorage.setItem('authToken', token);
      isAuthenticated.value = true;
    }

    // Réinitialiser le formulaire
    resetForm();

    // Redirection vers la page d'accueil
    await router.push('/');
  } catch (error: any) {
    errorMessage.value = (error as Error).message;
  }
};

// Déconnexion de l'utilisateur
const logout = () => {
  localStorage.removeItem('authToken'); // Retirer le jeton
  isAuthenticated.value = false; // Mettre l'état de l'utilisateur à non authentifié
  router.push('/'); // Rediriger vers la page d'accueil
};

// Vérification si l'utilisateur est déjà authentifié
onMounted(async () => {
  const token = localStorage.getItem('authToken');
  if (token) {
    const isValid = await ApiRequest.BooleanVerifyToken(token);
    if (isValid) {
      isAuthenticated.value = true;
    }
  }
});
</script>

<template>
  <div>
    <div class="container" v-if="!isAuthenticated">
      <div class="card">
        <h2>{{ isLoginMode ? 'Connexion' : 'Inscription' }}</h2>
        <form @submit.prevent="isLoginMode ? login() : register()">
          <div v-if="!isLoginMode">
            <input type="text" v-model="pseudo" placeholder="Pseudo" required/>
            <input type="text" v-model="nom" placeholder="Nom" required/>
            <input type="text" v-model="prenom" placeholder="Prénom" required/>
          </div>

          <input type="email" v-model="mail" placeholder="Email" required/>
          <input type="password" v-model="mdp" placeholder="Mot de passe" required/>
          <button type="submit">{{ isLoginMode ? 'Se connecter' : 'S\'inscrire' }}</button>
        </form>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
        <button class="toggle-btn" @click="toggleMode">{{ isLoginMode ? 'Créer un compte' : 'Se connecter' }}</button>
      </div>
    </div>
    <div v-if="isAuthenticated" class="welcome">
      <header>
        <h2>Bienvenue ! Vous êtes connecté.</h2>
        <h3 class="objet-title">Liste des objets</h3>
        <router-link class="addObjet" to="/add-object">Ajouter un objet</router-link>
        <button class="disconected" @click="logout">Déconnexion</button>
      </header>
      <main>
        <form class="search-bar">
          <input type="text" v-model="searchText" placeholder="Rechercher par texte"/>
          <input type="text" v-model="searchCategorie" placeholder="Rechercher par catégorie"/>
          <input type="text" v-model="searchUser" placeholder="Rechercher par utilisateur"/>
          <button @click="">Rechercher</button>
        </form>
        <ObjetScreen class="object-screen"/>
      </main>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f1f1f1f1;
}

.welcome {
  text-align: center;
  padding: 20px;
  font-size: 20px;
  width: 90%;
  height: 100%;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
}


header {
  top: 0;
  width: 100%;
  background: white;
  z-index: 1000;
  padding: 10px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

main {
  margin-top: 1em;
  padding: 0.5em;
  flex: 1;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.search-bar {
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.object-screen {
  margin-top: 1em;
  padding: 0.5em;
  width: 100%;
  display: flex;
  flex: 1;
  justify-content: center;
}

.disconected {
  margin-left: 20px;
  transition: 0.5s ease-in-out;
}

.disconected:hover {
  transition: background-color 0.3s ease-in-out;
}

.addObjet {
  color: black;
}

.addObjet:hover {
  color: blue;
  transition: 0.5s ease-in-out;
}

.card {
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin: 0 auto;
  background: white;
}

h2 {
  margin-bottom: 1rem;
  color: #333;
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

button {
  background: #667eea;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background: #5a67d8;
}

.toggle-btn {
  background: none;
  color: #667eea;
  margin-top: 10px;
  text-decoration: underline;
  cursor: pointer;
}

.error {
  color: red;
  margin-top: 10px;
}

.objet-title {
  margin-top: 20px;
}
</style>
