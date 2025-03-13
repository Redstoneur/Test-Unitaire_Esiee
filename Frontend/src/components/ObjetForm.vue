<!--
  @file Frontend/src/components/ObjetForm.vue
  @description A Vue component for a form to add an object.
-->

<script setup lang="ts">
import {computed} from 'vue';

// Import types
import CategorieObjet from '../Types/CategorieObjet';

/**
 * Component properties definition.
 *
 * @property {string} nomObjet - The object name.
 * @property {string} descriptionObjet - The object description.
 * @property {string} categorieObjet - The object category.
 * @property {string} errorMessage - The error message to display.
 * @property {string} successMessage - The success message to display.
 */
const props = defineProps({
  nomObjet: String,
  descriptionObjet: String,
  categorieObjet: String,
  errorMessage: String,
  successMessage: String
});

/**
 * List of object categories.
 * @type {string[]}
 */
const categories: string[] = Object.values(CategorieObjet);

/**
 * Define component emit events.
 * @event submit - Emitted when the form is submitted.
 * @event update:nomObjet - Emitted when the object name is updated.
 * @event update:descriptionObjet - Emitted when the object description is updated.
 * @event update:categorieObjet - Emitted when the object category is updated.
 */
const emit = defineEmits([
  'submit',
  'update:nomObjet',
  'update:descriptionObjet',
  'update:categorieObjet'
]);

/**
 * Computed property for the object name.
 * Provides two-way binding by returning the current name and emitting an update event on change.
 */
const nomObjet = computed({
  get: () => props.nomObjet,
  set: (value: string) => emit('update:nomObjet', value)
});

/**
 * Computed property for the object description.
 * Provides two-way binding by returning the current description and emitting an update event on change.
 */
const descriptionObjet = computed({
  get: () => props.descriptionObjet,
  set: (value: string) => emit('update:descriptionObjet', value)
});

/**
 * Computed property for the object category.
 * Provides two-way binding by returning the current category and emitting an update event on change.
 */
const categorieObjet = computed({
  get: () => props.categorieObjet,
  set: (value: string) => emit('update:categorieObjet', value)
});

/**
 * Handles the form submission.
 * Prevents the default form submission behavior and emits the 'submit' event.
 *
 * @param {Event} event - The event object from the form submission.
 */
const onSubmit = (event: Event) => {
  event.preventDefault();
  emit('submit');
};
</script>

<template>
  <section>
    <!-- Display error message if exists -->
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    <!-- Display success message if exists -->
    <p v-if="successMessage" class="success">{{ successMessage }}</p>
    <!-- Form for inputting object details -->
    <form @submit="onSubmit">
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