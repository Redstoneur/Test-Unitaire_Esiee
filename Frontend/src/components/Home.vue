<!--
  @file Frontend/src/components/Home.vue
  @description Home component that displays the welcome screen, search bar and list of objects.
  It uses the AppHeader and ObjetScreen components to render the header and object list.
-->
<script setup lang="ts">
import {onMounted, Ref, ref} from 'vue';
import {Router, useRouter} from 'vue-router';

// Import components
import AppHeader from './AppHeader.vue';
import ObjetScreen from './ObjetScreen.vue';

// Import API functions
import {BooleanVerifyToken, DeleteObjet, GetEchanges, GetObjets} from "../Function/ApiRequest";

// Import types
import CategorieObjet from "../Types/CategorieObjet";
import Objet from "../Types/Objet";
import EtatEchange from "../Types/EtatEchange";

/**
 * Router instance for navigating between routes.
 * @type {Router}
 */
const router: Router = useRouter();

/**
 * Reactive boolean indicating whether the user is authenticated.
 * @type {Ref<boolean>}
 */
const isAuthenticated: Ref<boolean> = ref(true);

/**
 * Reactive string used to display error messages.
 * @type {Ref<string>}
 */
const errorMessage: Ref<string> = ref('');

/**
 * Reactive string for the search query text.
 * @type {Ref<string>}
 */
const searchText: Ref<string> = ref('');

/**
 * Reactive string for filtering objects by user.
 * @type {Ref<string>}
 */
const searchUser: Ref<string> = ref('');

/**
 * Reactive string for filtering objects by category.
 * @type {Ref<string>}
 */
const searchCategorie: Ref<string> = ref('');

/**
 * Reactive array to store objects fetched from the API.
 * @type {Ref<Objet[]>}
 */
const objets: Ref<Objet[]> = ref([]);

/**
 * Array of categories extracted from the CategorieObjet enum.
 * @type {string[]}
 */
const categories: string[] = Object.values(CategorieObjet);

/**
 * Logs out the user.
 *
 * This function removes the authentication token from local storage,
 * updates the authentication state, and navigates the user to the login page.
 */
const logout = () => {
  localStorage.removeItem('authToken');
  isAuthenticated.value = false;
  router.push('/login');
};

/**
 * Retrieves objects from the API and updates the objets array.
 *
 * @async
 * @function fetchObjets
 */
const fetchObjets = async () => {
  // Call the API to retrieve objects.
  const response = await GetObjets();

  // Verify that the response is an instance of Response and that it is OK.
  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = 'Erreur lors de la récupération des objets';
    return;
  }

  // Parse the JSON data from the response.
  const data = await response.json();

  // Map the data to the expected object structure.
  objets.value = data.map((objet: any) => ({
    id: objet.id,
    nom: objet.nom,
    description: objet.description,
    categorie: objet.categorie,
    idUtilisateur: objet.idUtilisateur,
    utilisateur: objet.utilisateur,
    showInput: false,
    enEchange: false,
    idEchange: null
  }));

  // Filter objects if a category is selected.
  if (searchCategorie.value) {
    objets.value = objets.value.filter(o => o.categorie === searchCategorie.value);
  }

  // Filter objects if a search text is provided.
  if (searchText.value) {
    objets.value = objets.value.filter(o =>
        o.nom.toLowerCase().includes(searchText.value.toLowerCase()) ||
        o.description.toLowerCase().includes(searchText.value.toLowerCase())
    );
  }

  // Filter objects if a search user is provided.
  if (searchUser.value) {
    objets.value = objets.value.filter(o =>
        o.utilisateur.toLowerCase().includes(searchUser.value.toLowerCase())
    );
  }

  // Clear any previous error messages.
  errorMessage.value = '';

  // Fetch the exchange information related to the objects.
  await fetchEchanges();
};

const fetchEchanges = async () => {
  const response = await GetEchanges();

  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = 'Erreur lors de la récupération des échanges';
    return;
  }

  const echanges = await response.json();

  objets.value.forEach(objet => {
    if (echanges.some((e: any) =>
        (e.objetDemande.id === objet.id || e.objetPropose.id === objet.id) && (e.etatEchange === EtatEchange.ATTENTE)
    )) {
      objet.enEchange = true;
      objet.idEchange = echanges.find((e: any) =>
          (e.objetDemande.id === objet.id || e.objetPropose.id === objet.id) && (e.etatEchange === EtatEchange.ATTENTE)
      ).id;
    }
  });

  errorMessage.value = '';
};

const handleSupprimerObjet = async (objetId: number) => {
  const token = localStorage.getItem('authToken');
  if (token && await BooleanVerifyToken(token)) {
    isAuthenticated.value = true;
    const response = await DeleteObjet(objetId, token);

    if (!(response instanceof Response) || !response.ok) {
      errorMessage.value = 'Erreur lors de la suppression';
      return;
    }

    objets.value = objets.value.filter(o => o.id !== objetId);
    errorMessage.value = '';
    return;
  }
  errorMessage.value = 'Vous n\'êtes pas Connecté';
  isAuthenticated.value = false;
};

const handleSearch = async () => {
  await fetchObjets();
};

const handleReset = async () => {
  searchText.value = '';
  searchCategorie.value = '';
  await fetchObjets();
};

onMounted(async () => {
  const token = localStorage.getItem('authToken');
  if (!(token && await BooleanVerifyToken(token))) {
    await router.push('/login');
  }
  await fetchObjets();
});
</script>

<template>
  <div class="welcome">
    <AppHeader
        title="Bienvenue ! Vous êtes connecté."
        subtitle="Liste des objets"
        routerLinkLabel="Ajouter un objet"
        routerLinkTarget="/add-object"
        :showLogout="true"
        @logout="logout"
    />
    <main>
      <form class="search-bar" @submit.prevent="handleSearch">
        <input type="text" v-model="searchText" placeholder="Rechercher par texte"/>
        <input type="text" v-model="searchUser" placeholder="Rechercher par utilisateur"/>
        <select v-model="searchCategorie">
          <option value="" disabled selected>Sélectionner une catégorie</option>
          <option v-for="category in categories" :key="category" :value="category">
            {{ category }}
          </option>
        </select>
        <div style="display: flex; justify-content: center; gap: 10px;">
          <button type="submit">Rechercher</button>
          <button type="reset" @click="handleReset">Réinitialiser</button>
        </div>
      </form>
      <ObjetScreen :objets="objets" :handleSupprimerObjet="handleSupprimerObjet" class="object-screen"/>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </main>
  </div>
</template>

<style scoped>
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