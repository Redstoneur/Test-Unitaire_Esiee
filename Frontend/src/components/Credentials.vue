<!--
  @file Frontend/src/components/Credentials.vue
  @description A Vue component handling user authentication including login and registration.
-->

<script setup lang="ts">
import {Ref, ref} from 'vue';
import {useRouter} from 'vue-router';
import {BooleanVerifyToken, Login, Register} from "../Function/ApiRequest";

/**
 * Reactive variable to check if the component is in login mode.
 * @type {Ref<boolean>}
 */
const isLoginMode: Ref<boolean> = ref(true);

/**
 * Reactive variable for holding the user's pseudo.
 * @type {Ref<string>}
 */
const pseudo: Ref<string> = ref('');

/**
 * Reactive variable for holding the user's password.
 * @type {Ref<string>}
 */
const mdp: Ref<string> = ref('');

/**
 * Reactive variable for holding the user's email.
 * @type {Ref<string>}
 */
const mail: Ref<string> = ref('');

/**
 * Reactive variable for holding the user's last name.
 * @type {Ref<string>}
 */
const nom: Ref<string> = ref('');

/**
 * Reactive variable for holding the user's first name.
 * @type {Ref<string>}
 */
const prenom: Ref<string> = ref('');

/**
 * Reactive variable for storing error messages.
 * @type {Ref<string>}
 */
const errorMessage: Ref<string> = ref('');

/**
 * Router instance for navigating between routes.
 */
const router = useRouter();

/**
 * Resets the form by clearing all reactive fields.
 */
const resetForm = () => {
  pseudo.value = '';
  mdp.value = '';
  mail.value = '';
  nom.value = '';
  prenom.value = '';
  errorMessage.value = '';
};

/**
 * Toggles the mode between login and registration.
 * Also resets the form fields when the mode changes.
 */
const toggleMode = () => {
  isLoginMode.value = !isLoginMode.value;
  resetForm();
};

/**
 * Handles user registration by sending a request to the API.
 * If successful, stores the authentication token and navigates to home.
 *
 * @async
 */
const register = async () => {
  const response = await Register(
      pseudo.value,
      mdp.value,
      mail.value,
      nom.value,
      prenom.value
  );

  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = "Erreur lors de l'inscription";
    return;
  }

  const data = await response.json();

  // Save token and verify its validity
  const token = data.token;
  const isValid = await BooleanVerifyToken(token);
  if (isValid) {
    localStorage.setItem('authToken', token);
  }

  resetForm();
  errorMessage.value = '';
  await router.push('/');
};

/**
 * Handles user login by sending a request to the API.
 * If successful, stores the authentication token and navigates to home.
 *
 * @async
 */
const login = async () => {
  const response = await Login(mail.value, mdp.value);

  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = 'Échec de la connexion';
    return;
  }

  const data = await response.json();
  const token = data.token;
  const isValid = await BooleanVerifyToken(token);
  if (!isValid) {
    errorMessage.value = 'Échec de la connexion';
    return;
  }
  localStorage.setItem('authToken', token);
  errorMessage.value = '';
  await router.push('/');
};
</script>

<template>
  <div class="container">
    <div class="card">
      <!-- Display heading based on mode: Login or Registration -->
      <h2>{{ isLoginMode ? 'Connexion' : 'Inscription' }}</h2>
      <form @submit.prevent="isLoginMode ? login() : register()">
        <!-- Registration specific inputs -->
        <div v-if="!isLoginMode">
          <input type="text" v-model="pseudo" placeholder="Pseudo" required/>
          <input type="text" v-model="nom" placeholder="Nom" required/>
          <input type="text" v-model="prenom" placeholder="Prénom" required/>
        </div>
        <!-- Common inputs -->
        <input type="email" v-model="mail" placeholder="Email" required/>
        <input type="password" v-model="mdp" placeholder="Mot de passe" required/>
        <button type="submit">{{ isLoginMode ? 'Se connecter' : 'S\'inscrire' }}</button>
      </form>
      <!-- Display error message if any -->
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <!-- Button to toggle between login and registration mode -->
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
