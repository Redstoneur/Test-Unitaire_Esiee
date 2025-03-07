<script setup lang="ts">
import { ref, onMounted } from 'vue';
import ObjetScreen from './ObjetScreen.vue';
import { useRouter } from 'vue-router';

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

// Vérification si l'utilisateur est déjà authentifié
onMounted(() => {
  const token = localStorage.getItem('authToken');
  if (token) {
    isAuthenticated.value = true;
  }
});

// Changer le mode (connexion/inscription)
const toggleMode = () => {
  isLoginMode.value = !isLoginMode.value;
};

// Inscription de l'utilisateur
const register = async () => {
  try {
    const response = await fetch('http://localhost:3000/api/credentials/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        pseudo: pseudo.value,
        mdp: mdp.value,
        mail: mail.value,
        nom: nom.value,
        prenom: prenom.value,
      }),
    });

    if (!response.ok) throw new Error('Erreur lors de l\'inscription');
    toggleMode();
  } catch (error) {
    errorMessage.value = error.message;
  }
};

// Connexion de l'utilisateur
const login = async () => {
  try {
    const response = await fetch('http://localhost:3000/api/credentials/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ mail: mail.value, mdp: mdp.value }),
    });

    if (!response.ok) throw new Error('Échec de la connexion');
    const data = await response.json();

    // Sauvegarder le jeton dans localStorage
    localStorage.setItem('authToken', data.token);

    // Changer l'état d'authentification
    isAuthenticated.value = true;
    router.push('/'); // Rediriger vers la page d'accueil après la connexion
  } catch (error) {
    errorMessage.value = error.message;
  }
};

// Déconnexion de l'utilisateur
const logout = () => {
  localStorage.removeItem('authToken'); // Retirer le jeton
  isAuthenticated.value = false; // Mettre l'état de l'utilisateur à non authentifié
  router.push('/'); // Rediriger vers la page d'accueil
};
</script>

<template>
  <div>
    <div class="container" v-if="!isAuthenticated">
      <div class="card">
        <h2>{{ isLoginMode ? 'Connexion' : 'Inscription' }}</h2>
        <form @submit.prevent="isLoginMode ? login() : register()">
          <div v-if="!isLoginMode">
            <input type="text" v-model="pseudo" placeholder="Pseudo" required />
            <input type="text" v-model="nom" placeholder="Nom" required />
            <input type="text" v-model="prenom" placeholder="Prénom" required />
          </div>
          <input type="email" v-model="mail" placeholder="Email" required />
          <input type="password" v-model="mdp" placeholder="Mot de passe" required />
          <button type="submit">{{ isLoginMode ? 'Se connecter' : 'S\'inscrire' }}</button>
        </form>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
        <button class="toggle-btn" @click="toggleMode">{{ isLoginMode ? 'Créer un compte' : 'Se connecter' }}</button>
      </div>
    </div>
    <div v-if="isAuthenticated" class="welcome">
      <h2>Bienvenue ! Vous êtes connecté.</h2>
      <h3 class="objet-titlt">Liste des objets</h3>
      <router-link to="/add-object">Ajouter un objet</router-link> <button @click="logout">Déconnexion</button>
      <div>
        <ObjetScreen />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #d7cece;
}

.card {
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin: 0 auto;
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

.welcome {
  text-align: center;
  padding: 20px;
  font-size: 20px;
}
</style>
