<script setup lang="ts">
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {BooleanVerifyToken, Login, Register} from "../Function/ApiRequest";

const isLoginMode = ref(true);
const pseudo = ref('');
const mdp = ref('');
const mail = ref('');
const nom = ref('');
const prenom = ref('');
const errorMessage = ref('');
const router = useRouter();

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
    const response = await Register(
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
    const isValid = await BooleanVerifyToken(token);
    if (isValid) {
      localStorage.setItem('authToken', token);
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
// Changer le mode (connexion/inscription)
const toggleMode = () => {
  isLoginMode.value = !isLoginMode.value;
  resetForm(); // Réinitialiser les champs quand on change de mode
};

const login = async () => {
  try {
    const response = await Login(mail.value, mdp.value);
    if (response instanceof Error || !response.ok)
      throw new Error('Échec de la connexion');

    const data = await response.json();
    const token = data.token;
    const isValid = await BooleanVerifyToken(token);
    if (isValid) {
      localStorage.setItem('authToken', token);
      await router.push('/');
    }
  } catch (error: any) {
    errorMessage.value = (error as Error).message;
  }
};
</script>

<template>
  <div class="container">
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
</template>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #f1f1f1f1;
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
  padding: 0.5em;
  flex: 1;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.card {
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin: 0 auto;
  background: white;
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

input, select {
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

@media (max-width: 768px) {
  main {
    width: 90%;
  }
}

</style>