<!--
  @file Frontend/src/components/addObjetScreen.vue
  @description A Vue component for adding an object using a form.
  It handles form submission and displays a modal on success.
-->

<script setup lang="ts">
import {Ref, ref} from 'vue';
import {useRouter} from 'vue-router';

// Import child component for the header
import AppHeader from './AppHeader.vue';

// Import API request function to add an object
import {AddObjet} from '../Function/ApiRequest';

// Import types for object data
import ObjetDTO from '../Types/ObjetDTO';
import CategorieObjet from '../Types/CategorieObjet';

// Create a router instance for navigation
const router = useRouter();

/**
 * Reactive variable holding the object name.
 * @type {Ref<string>}
 */
const nomObjet: Ref<string> = ref('');

/**
 * Reactive variable holding the object description.
 * @type {Ref<string>}
 */
const descriptionObjet: Ref<string> = ref('');

/**
 * Reactive variable holding the object category.
 * The value is either one of the categories or an empty string.
 * @type {Ref<CategorieObjet | "">}
 */
const categorieObjet: Ref<CategorieObjet | ""> = ref<CategorieObjet | ''>('');

/**
 * Reactive variable for storing error messages.
 * @type {Ref<string>}
 */
const errorMessage: Ref<string> = ref('');

/**
 * Reactive variable for storing success messages.
 * @type {Ref<string>}
 */
const successMessage: Ref<string> = ref('');

/**
 * Flag indicating whether the success modal is visible.
 * @type {Ref<boolean>}
 */
const showSuccessModal: Ref<boolean> = ref(false);

/**
 * Reactive variable storing the added object data returned from the API.
 * @type {Ref<ObjetDTO | null>}
 */
const addedObjet: Ref<ObjetDTO | null> = ref<ObjetDTO | null>(null);

/**
 * An array of available categories for selection.
 */
const categories = Object.values(CategorieObjet);

/**
 * Authentication token retrieved from local storage.
 * @type {string}
 */
const authToken: string = localStorage.getItem('authToken') || '';

/**
 * Handles the form submission process.
 * Constructs an object, sends an API request to add it, updates success or error messages,
 * resets form fields and shows the success modal in case of success.
 *
 * @async
 */
const handleSubmit = async () => {
  // Creating the object with current form values
  const objet: ObjetDTO = {
    nom: nomObjet.value,
    description: descriptionObjet.value,
    categorie: categorieObjet.value as CategorieObjet,
  };

  // Send the API request to add the object
  const response = await AddObjet(objet, authToken);

  // Error handling when request fails or response is invalid
  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = "Erreur lors de l'ajout de l'objet";
    return;
  }

  // Update state on successful addition
  addedObjet.value = {...objet};
  successMessage.value = 'Objet ajouté avec succès !';
  showSuccessModal.value = true;

  // Reset form fields
  nomObjet.value = '';
  descriptionObjet.value = '';
  categorieObjet.value = '';

  // Clear any previous error message
  errorMessage.value = '';
};

/**
 * Closes the success modal to allow the user to continue adding objects.
 */
const continueAdding = () => {
  showSuccessModal.value = false;
};

/**
 * Navigates back to the home page using the Vue router.
 */
const returnHome = () => {
  router.push('/');
};
</script>

<template>
  <div class="container">
    <!-- Header Component for the Add Object Screen -->
    <AppHeader
        title="Ajouter un Objet"
        routerLinkLabel="Retour"
        routerLinkTarget="/"
    />
    <main>
      <!-- Display error message if exists -->
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <!-- Display success message if exists -->
      <p v-if="successMessage" class="success">{{ successMessage }}</p>
      <!-- Form for inputting object details -->
      <form @submit.prevent="handleSubmit()">
        <div class="form-group">
          <!-- Input for object name -->
          <label for="nomObjet">Nom de l&apos;Objet</label>
          <input v-model="nomObjet" type="text" id="nomObjet" required/>
        </div>
        <div class="form-group">
          <!-- Textarea for object description -->
          <label for="descriptionObjet">Description de l&apos;Objet</label>
          <textarea v-model="descriptionObjet" id="descriptionObjet" required></textarea>
        </div>
        <div class="form-group">
          <!-- Dropdown selector for object category -->
          <label for="categorieObjet">Catégorie</label>
          <select v-model="categorieObjet" id="categorieObjet" required>
            <option value="" disabled selected>Sélectionner une catégorie</option>
            <option v-for="category in categories" :key="category" :value="category">
              {{ category }}
            </option>
          </select>
        </div>
        <!-- Submit button to add the object -->
        <button type="submit">Ajouter l&apos;Objet</button>
      </form>
      <!-- Success Modal displayed when an object is successfully added -->
      <div v-if="showSuccessModal" class="modal">
        <div class="modal-content">
          <h2>Objet ajouté avec succès !</h2>
          <div class="modal-details">
            <p><strong>Nom :</strong> {{ addedObjet?.nom }}</p>
            <p><strong>Description :</strong> {{ addedObjet?.description }}</p>
            <p><strong>Catégorie :</strong> {{ addedObjet?.categorie }}</p>
          </div>
          <div class="modal-actions">
            <!-- Button to continue adding new objects -->
            <button class="modal-btn continue-btn" @click="continueAdding">
              Continuer à ajouter
            </button>
            <!-- Button to navigate back to home -->
            <button class="modal-btn home-btn" @click="returnHome">
              Revenir à l&apos;accueil
            </button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
/* Container styles for the main wrapper */
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

/* Margin for form groups */
.form-group {
  margin-bottom: 15px;
}

/* Label style for form elements */
label {
  display: block;
  text-align: left;
  margin-bottom: 5px;
}

/* Style for input, select, and textarea elements */
input,
select,
textarea {
  width: 100%;
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

/* General style for buttons */
button {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Hover effect for buttons */
button:hover {
  background-color: #45a049;
}

/* Error message styling */
.error {
  color: red;
}

/* Success message styling */
.success {
  color: green;
}

/* Modal container styles */
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

/* Modal content box styling */
.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 500px;
  text-align: center;
}

/* Styles for details within the modal */
.modal-details p {
  margin: 10px 0;
}

/* Container for modal actions */
.modal-actions {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
}

/* General style for modal buttons */
.modal-btn {
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
}

/* Hover effect for modal buttons */
.modal-btn:hover {
  background-color: #45a049;
}
</style>
