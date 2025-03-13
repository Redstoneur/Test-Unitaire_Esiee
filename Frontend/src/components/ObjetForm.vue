<!--
  @file Frontend/src/components/ObjetForm.vue
  @description A Vue component for a form to add an object.
  It now handles the form submission process.
-->

<script setup lang="ts">
import {computed, ref} from 'vue';
import {AddObjet} from '../Function/ApiRequest';
import ObjetDTO from '../Types/ObjetDTO';
import CategorieObjet from '../Types/CategorieObjet';

/**
 * Component properties.
 *
 * Defines the component's properties and their default values.
 *
 * @property {string} nomObjet - The name of the object.
 * @property {string} descriptionObjet - The object description.
 * @property {string} categorieObjet - The category of the object.
 * @property {string} authToken - The authentication token.
 */
const props = defineProps({
  nomObjet: { type: String, default: '' },
  descriptionObjet: { type: String, default: '' },
  categorieObjet: { type: String, default: '' },
  authToken: { type: String, default: '' }
});

// Define component emit events.
const emit = defineEmits([
  'update:nomObjet',
  'update:descriptionObjet',
  'update:categorieObjet',
  'object-added',
  'object-added-id'
]);

/**
 * Computed property for the object name.
 * Provides two-way binding by returning the current name and emitting an update event on change.
 */
const nomObjetComputed = computed({
  get: () => props.nomObjet,
  set: (value: string) => emit('update:nomObjet', value)
});

/**
 * Computed property for the object description.
 * Provides two-way binding by returning the current description and emitting an update event on change.
 */
const descriptionObjetComputed = computed({
  get: () => props.descriptionObjet,
  set: (value: string) => emit('update:descriptionObjet', value)
});

/**
 * Computed property for the object category.
 * Provides two-way binding by returning the current category and emitting an update event on change.
 */
const categorieObjetComputed = computed({
  get: () => props.categorieObjet,
  set: (value: string) => emit('update:categorieObjet', value)
});

/**
 * List of object categories.
 * @type {string[]}
 */
const categories: string[] = Object.values(CategorieObjet);

// Local reactive state for messages.
const errorMessage = ref('');
const successMessage = ref('');

/**
 * Handles the form submission process.
 * Constructs an object, sends an API request to add it, updates success or error messages,
 * resets form fields and emits an event on success.
 *
 * @async
 */
const handleSubmit = async (event: Event) => {
  event.preventDefault();

  // Creating the object with current form values
  const objet: ObjetDTO = {
    nom: nomObjetComputed.value,
    description: descriptionObjetComputed.value,
    categorie: categorieObjetComputed.value as CategorieObjet,
  };

  // Send the API request to add the object
  const response = await AddObjet(objet, props.authToken || '');

  // Error handling when request fails or response is invalid
  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = "Erreur lors de l'ajout de l'objet";
    return;
  }

  // Update state on successful addition
  successMessage.value = 'Objet ajouté avec succès !';

  const data = await response.json();

  // Emit event to parent with the added object and success message
  emit('object-added', {data, successMessage: successMessage.value});
  emit('object-added-id', {id: data.id, successMessage: successMessage.value});

  // Reset form fields and clear any previous error message
  nomObjetComputed.value = '';
  descriptionObjetComputed.value = '';
  categorieObjetComputed.value = '';
  errorMessage.value = '';
};
</script>

<template>
  <section>
    <!-- Display error message if exists -->
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    <!-- Display success message if exists -->
    <p v-if="successMessage" class="success">{{ successMessage }}</p>
    <!-- Form for inputting object details -->
    <form @submit="handleSubmit">
      <div class="form-group">
        <!-- Input for object name -->
        <label for="nomObjet">Nom de l&apos;Objet</label>
        <input v-model="nomObjetComputed" type="text" id="nomObjet" required/>
      </div>
      <div class="form-group">
        <!-- Textarea for object description -->
        <label for="descriptionObjet">Description de l&apos;Objet</label>
        <textarea v-model="descriptionObjetComputed" id="descriptionObjet" required></textarea>
      </div>
      <div class="form-group">
        <!-- Dropdown selector for object category -->
        <label for="categorieObjet">Catégorie</label>
        <select v-model="categorieObjetComputed" id="categorieObjet" required>
          <option value="" disabled>Sélectionner une catégorie</option>
          <option v-for="category in categories" :key="category" :value="category">
            {{ category }}
          </option>
        </select>
      </div>
      <!-- Submit button to add the object -->
      <button type="submit">Ajouter l&apos;Objet</button>
    </form>
  </section>
</template>

<style scoped>
/* (Keep existing CSS unchanged) */
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
</style>
