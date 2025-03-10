<script setup lang="ts">
import {ref} from 'vue';
import apiRequest from "../Class/ApiRequest";
import Objet from "../Types/Objet";
import CategorieObjet from "../Types/CategorieObjet";

const nomObjet = ref('');
const descriptionObjet = ref('');
const categorieObjet = ref<CategorieObjet | ''>('');

const errorMessage = ref('');
const successMessage = ref('');

const categories = Object.values(CategorieObjet);
const authToken: string = localStorage.getItem('authToken') || '';
const handleSubmit = async () => {
  const objet: Objet = {
    nom: nomObjet.value,
    description: descriptionObjet.value,
    categorie: categorieObjet.value as CategorieObjet,
  };

  try {
    const response = await apiRequest.AddObjet(objet, authToken);

    if (!(response instanceof Response) || !response.ok) {
      throw new Error('Erreur lors de l\'ajout de l\'objet');
    }

    const data = response.json();
    console.log("data", data);
    successMessage.value = 'Objet ajouté avec succès !';

    // Reset form
    nomObjet.value = '';
    descriptionObjet.value = '';
    categorieObjet.value = '';

  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

</script>

<template>
  <div class="container">
    <h2>Ajouter un Objet</h2>
    <router-link to="/">Retour</router-link>

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

input, select, textarea {
  width: 100%;
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  background-color: #4CAF50;
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
</style>
