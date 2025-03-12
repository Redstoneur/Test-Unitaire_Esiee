<script setup lang="ts">
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {AddObjet} from '../Function/ApiRequest';
import ObjetDTO from '../Types/ObjetDTO';
import CategorieObjet from '../Types/CategorieObjet';

const router = useRouter();

const nomObjet = ref('');
const descriptionObjet = ref('');
const categorieObjet = ref<CategorieObjet | ''>('');

const errorMessage = ref('');
const successMessage = ref('');
const showSuccessModal = ref(false);
const addedObjet = ref<ObjetDTO | null>(null);

const categories = Object.values(CategorieObjet);
const authToken: string = localStorage.getItem('authToken') || '';

const handleSubmit = async () => {
  const objet: ObjetDTO = {
    nom: nomObjet.value,
    description: descriptionObjet.value,
    categorie: categorieObjet.value as CategorieObjet,
  };

  try {
    const response = await AddObjet(objet, authToken);

    if (!(response instanceof Response) || !response.ok) {
      throw new Error("Erreur lors de l'ajout de l'objet");
    }

    // const data = response.json();
    // console.log("data", data);

    // Show success modal
    addedObjet.value = {...objet};
    successMessage.value = 'Objet ajouté avec succès !';
    showSuccessModal.value = true;

    // Reset form
    nomObjet.value = '';
    descriptionObjet.value = '';
    categorieObjet.value = '';

  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

const continueAdding = () => {
  showSuccessModal.value = false;
};

const returnHome = () => {
  router.push('/');
};
</script>

<template>
  <div class="container">
    <header>
      <h2>Ajouter un Objet</h2>
      <router-link to="/">Retour</router-link>
    </header>
    <main>
      <!-- Affichage des messages d'erreur ou de succès -->
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <p v-if="successMessage" class="success">{{ successMessage }}</p>

    <!-- Formulaire d'ajout d'objet -->
    <form @submit.prevent="handleSubmit()">
      <div class="form-group">
        <label for="nomObjet">Nom de l'Objet</label>
        <input v-model="nomObjet" type="text" id="nomObjet" required/>
      </div>

      <div class="form-group">
        <label for="descriptionObjet">Description de l'Objet</label>
        <textarea v-model="descriptionObjet" id="descriptionObjet" required></textarea>
      </div>

      <div class="form-group">
        <label for="categorieObjet">Catégorie</label>
        <select v-model="categorieObjet" id="categorieObjet" required>
          <option value="" disabled selected>Sélectionner une catégorie</option>
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
      </div>

      <button type="submit">Ajouter l'Objet</button>
    </form>

      <!-- Success Modal -->
      <div v-if="showSuccessModal" class="modal">
        <div class="modal-content">
          <h2>Objet ajouté avec succès !</h2>
          <div class="modal-details">
            <p><strong>Nom :</strong> {{ addedObjet?.nom }}</p>
            <p><strong>Description :</strong> {{ addedObjet?.description }}</p>
            <p><strong>Catégorie :</strong> {{ addedObjet?.categorie }}</p>
          </div>
          <div class="modal-actions">
            <button class="modal-btn continue-btn" @click="continueAdding">Continuer à ajouter</button>
            <button class="modal-btn home-btn" @click="returnHome">Revenir à l'accueil</button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  text-align: left;
  margin-bottom: 5px;
}

input,
select,
textarea {
  width: 100%;
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

.error {
  color: red;
}

.success {
  color: green;
}

/* Modal Styles */
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 500px;
  text-align: center;
}

.modal-details p {
  margin: 10px 0;
}

.modal-actions {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
}

.modal-btn {
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
}

.modal-btn:hover {
  background-color: #45a049;
}
</style>
