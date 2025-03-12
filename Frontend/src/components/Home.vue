<script setup lang="ts">
import {onMounted, ref} from 'vue';
import {useRouter} from 'vue-router';

// Importation des composants
import AppHeader from './AppHeader.vue';
import ObjetScreen from './ObjetScreen.vue';

// Importation des fonctions de l'API
import {BooleanVerifyToken, DeleteObjet,
  GetEchanges, GetObjets} from "../Function/ApiRequest";

// Importation des types
import CategorieObjet from "../Types/CategorieObjet";
import Objet from "../Types/Objet";

const router = useRouter();
const isAuthenticated = ref(true);
const errorMessage = ref('');
const searchText = ref('');
const searchCategorie = ref('');
const objets = ref<Objet[]>([]);
const categories = Object.values(CategorieObjet);

const logout = () => {
  localStorage.removeItem('authToken');
  isAuthenticated.value = false;
  router.push('/login');
};

const fetchObjets = async () => {
  try {
    const response = await GetObjets();
    if (response instanceof Error || !response.ok)
      throw new Error('Erreur lors de la récupération des objets');

    const data = await response.json();
    objets.value = data.map((objet: any) => ({
      id: objet.id,
      nom: objet.nom,
      description: objet.description,
      categorie: objet.categorie,
      idUtilisateur: objet.idUtilisateur,
      showInput: false,
      enEchange: false,
      idEchange: null
    }));
    if (searchCategorie.value) {
      objets.value = objets.value.filter(o => o.categorie === searchCategorie.value);
    }
    if (searchText.value) {
      objets.value = objets.value.filter(o =>
          o.nom.toLowerCase().includes(searchText.value.toLowerCase()) ||
          o.description.toLowerCase().includes(searchText.value.toLowerCase())
      );
    }
    await fetchEchanges();
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

const fetchEchanges = async () => {
  try {
    const response = await GetEchanges();
    if (response instanceof Error || !response.ok)
      throw new Error('Erreur lors de la récupération des échanges');

    const echanges = await response.json();
    objets.value.forEach(objet => {
      if (echanges.some((e: any) =>
          e.objetDemande.id === objet.id || e.objetPropose.id === objet.id
      )) {
        objet.enEchange = true;
        objet.idEchange = echanges.find((e: any) =>
            e.objetDemande.id === objet.id || e.objetPropose.id === objet.id
        ).id;
      }
    });
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

// Fonction pour supprimer un objet
const handleSupprimerObjet = async (objetId: number) => {
  try {
    const token = localStorage.getItem('authToken');
    if (token && await BooleanVerifyToken(token)) {
      isAuthenticated.value = true;
      const response = await DeleteObjet(objetId, token);

      if (response instanceof Error || !response.ok)
        throw new Error('Erreur lors de la suppression');

      objets.value = objets.value.filter(o => o.id !== objetId);
    } else {
      throw new Error('Vous n\'êtes pas Connecté');
    }
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
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
    router.push('/login');
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