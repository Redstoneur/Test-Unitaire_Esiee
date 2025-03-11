<script setup lang="ts">
import {ref, onMounted} from 'vue';
import ObjetScreen from './ObjetScreen.vue';
import {useRouter} from 'vue-router';
import {
  Register, BooleanVerifyToken, Login,
  GetObjets, GetEchanges, DeleteObjet
} from "../Function/ApiRequest";
import CategorieObjet from "../Types/CategorieObjet";
import Objet from "../Types/Objet";

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
const objets = ref<Objet[]>([]);

// Liste des catégories d'objets
const categories = Object.values(CategorieObjet);

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
    const response = await Login(mail.value, mdp.value);

    if (response instanceof Error || !response.ok)
      throw new Error('Échec de la connexion');

    const data = await response.json();

    // Sauvegarder le jeton et connexion immédiate
    const token = data.token;
    const isValid = await BooleanVerifyToken(token);
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

// Récupérer les objets et vérifier s'ils sont en échange
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

    // si la recherche par catégorie est activée
    // on filtre les objets par catégorie
    if (searchCategorie.value) {
      objets.value = objets.value.filter(o => o.categorie === searchCategorie.value);
    }

    // si la recherche par texte est activée
    // on filtre les objets par nom ou description
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

// Récupérer la liste des échanges
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
    } else
      throw new Error('Vous n\'êtes pas Connecté');
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

// Vérification si l'utilisateur est déjà authentifié
onMounted(async () => {
  const token = localStorage.getItem('authToken');
  isAuthenticated.value = !!(token && await BooleanVerifyToken(token));
  await fetchObjets();
});

// Fonction pour gérer la soumission du formulaire de recherche
const handleSearch = async () => {
  await fetchObjets();
};

const handleReset = async () => {
  searchText.value = '';
  searchCategorie.value = '';
  await fetchObjets();
};

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
        <form class="search-bar" @submit.prevent="handleSearch">
          <input type="text" v-model="searchText" placeholder="Rechercher par texte"/>
          <select v-model="searchCategorie" placeholder="Rechercher par catégorie">
            <option value="" disabled selected>Sélectionner une catégorie</option>
            <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
          </select>
          <div style="display: flex; justify-content: center; gap: 10px;">
            <button type="submit">Rechercher</button>
            <button type="reset" @click="handleReset">Réinitialiser</button>
          </div>
        </form>
        <ObjetScreen :objets="objets" :handleSupprimerObjet="handleSupprimerObjet" class="object-screen"/>
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

.objet-title {
  margin-top: 20px;
}
</style>
