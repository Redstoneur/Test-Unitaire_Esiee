<script setup lang="ts">
import { ref } from 'vue';

const pseudo = ref('');
const mdp = ref('');
const mail = ref('');
const nomUtilisateur = ref('');
const prenomUtilisateur = ref('');
const nomObjet = ref('');
const descriptionObjet = ref('');
const categorieObjet = ref('');

const errorMessage = ref('');
const successMessage = ref('');

const categories = [
  'MOBILIER',
  'JARDINAGE',
  'INFORMATIQUE',
  'GAMING',
  'OUTILS',
  'COLLECTION',
  'LITTERATURE',
  'VETEMENTS',
  'ELECTROMENAGER',
  'AUTRE'
]; // Liste des catégories mises à jour

const handleSubmit = async () => {
  const utilisateur = {
    pseudo: pseudo.value,
    mdp: mdp.value,
    mail: mail.value,
    nom: nomUtilisateur.value,
    prenom: prenomUtilisateur.value,
  };

  const objet = {
    utilisateur,
    nom: nomObjet.value,
    description: descriptionObjet.value,
    categorie: categorieObjet.value,
    dateCreation: new Date().toISOString(), // Utilisation de la date actuelle
  };

  try {
    const response = await fetch('http://localhost:3000/api/objets', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(objet),
    });

    if (!response.ok) {
      throw new Error('Erreur lors de l\'ajout de l\'objet');
    }

    successMessage.value = 'Objet ajouté avec succès !';
    // Reset form
    pseudo.value = '';
    mdp.value = '';
    mail.value = '';
    nomUtilisateur.value = '';
    prenomUtilisateur.value = '';
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
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="pseudo">Pseudo Utilisateur</label>
        <input v-model="pseudo" type="text" id="pseudo" required />
      </div>

      <div class="form-group">
        <label for="mdp">Mot de Passe</label>
        <input v-model="mdp" type="password" id="mdp" required />
      </div>

      <div class="form-group">
        <label for="mail">Email</label>
        <input v-model="mail" type="email" id="mail" required />
      </div>

      <div class="form-group">
        <label for="nomUtilisateur">Nom Utilisateur</label>
        <input v-model="nomUtilisateur" type="text" id="nomUtilisateur" required />
      </div>

      <div class="form-group">
        <label for="prenomUtilisateur">Prénom Utilisateur</label>
        <input v-model="prenomUtilisateur" type="text" id="prenomUtilisateur" required />
      </div>

      <div class="form-group">
        <label for="nomObjet">Nom de l'Objet</label>
        <input v-model="nomObjet" type="text" id="nomObjet" required />
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
