<!--
  @file Frontend/src/components/ObjetScreen.vue
  @description ObjetScreen component handles the display of objects.
  It supports showing object details, proposing exchanges, deleting objects and viewing exchange details via a modal.
-->
<script setup lang="ts">
import {defineProps, onMounted, Ref, ref} from 'vue';

// Import function
import {GetEchange, UserInformation} from "../Function/ApiRequest";

// Import types
import Objet from "../Types/Objet";

/**
 * Defines the properties accepted by the ObjetScreen component.
 * @property {Objet[]} objets - List of objects to display.
 * @property {function} handleSupprimerObjet - Function to handle object deletion.
 */
const props = defineProps<{
  objets: Objet[],
  handleSupprimerObjet: (objetId: number) => Promise<void>
}>();

/**
 * Error message displayed when an error occurs.
 * @type {Ref<string>}
 */
const errorMessage: Ref<string> = ref('');

/**
 * Logged in user id.
 * @type {Ref<string>}
 */
const utilisateurId: Ref<string> = ref('');

/**
 * Reactive variable to show/hide the modal.
 * @type {Ref<boolean>}
 */
const showModal: Ref<boolean> = ref(false);

/**
 * Selected object to view exchange details.
 * @type {Ref<Objet | null>}
 */
const selectedObjet: Ref<Objet | null> = ref(null);

/**
 * Details of the selected exchange.
 * @type {Ref<any>}
 */
const exchangeDetails: Ref<any> = ref(null);

/**
 * Authentication token stored in local storage.
 * @type {string}
 */
const authToken: string = localStorage.getItem('authToken') || '';

/**
 * Fetches the logged in user's id by making an asynchronous request to the UserInformation API.
 *
 * @async
 * @function fetchUtilisateurId
 */
const fetchUtilisateurId = async () => {
  // Make an API call to retrieve user information using the authToken.
  const response = await UserInformation(authToken);

  // Check if the response is valid and OK, otherwise set an error message.
  if (!(response instanceof Response) || !response.ok) {
    errorMessage.value = 'Erreur lors de la récupération de l\'utilisateur';
    return;
  }

  // Parse the response as JSON and update the utilisateurId ref with the retrieved id.
  const data = await response.json();
  utilisateurId.value = data.id;

  // Clear any previous error messages.
  errorMessage.value = '';
};

/**
 * Handles proposing an exchange for an object.
 *
 * This function searches through the provided objects for the one that matches the given ID.
 * If the object is found, it toggles the visibility of the input field that allows users to enter their proposal.
 *
 * @param {number} objetId - The ID of the object for which to propose an exchange.
 */
const handleProposerEchange = (objetId: number) => {
  // Search for the object in the list by its ID.
  const objet = props.objets.find(o => o.id === objetId);
  if (objet) {
    // Toggle the 'showInput' property to display or hide the exchange input field.
    objet.showInput = !objet.showInput;
  }
};

/**
 * Handles viewing the details of an exchange.
 *
 * Sets the selected object, displays the modal and fetches exchange details.
 *
 * @param {Objet} objet - The object to view the exchange details for.
 */
const handleVoirEchange = async (objet: Objet) => {
  // Assign the passed object to the reactive selected object
  selectedObjet.value = objet;
  // Set the modal visibility to true
  showModal.value = true;
  // Fetch and update exchange details using the object's exchange ID
  await fetchExchangeDetails(objet.idEchange);
};

/**
 * Fetches the details of an exchange.
 *
 * This function performs an asynchronous request to retrieve exchange details using the
 * provided exchange ID. It calls the GetEchange function with the exchange ID and authToken.
 * If an error occurs or the response is not OK, the errorMessage is set with a descriptive error.
 * Otherwise, it updates the exchangeDetails ref with the JSON data from the response and clears any previous error.
 *
 * @param {number} exchangeId - The ID of the exchange to fetch details for.
 */
const fetchExchangeDetails = async (exchangeId: number) => {
  const response = await GetEchange(exchangeId, authToken);

  if (response instanceof Error || !response.ok) {
    errorMessage.value = 'Erreur lors de la récupération de l\'échange';
    return;
  }

  exchangeDetails.value = await response.json();
  errorMessage.value = '';
};

/**
 * Component mounted lifecycle hook.
 * Fetches the logged in user's ID when the component is mounted.
 */
onMounted(async () => {
  await fetchUtilisateurId();
});
</script>

<template>
  <!-- Template for ObjetScreen component -->
  <section class="objet-zone">
    <!-- Error message display section -->
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

    <!-- Grid display for object cards -->
    <div class="grid">
      <!-- Loop through each object and display its corresponding card -->
      <div v-for="objet in objets" :key="objet.id" class="card">
        <!-- Display object title -->
        <h2>{{ objet.nom }}</h2>
        <!-- Display object description -->
        <p class="description">{{ objet.description }}</p>
        <!-- Display object category -->
        <p class="categorie"><strong>Catégorie :</strong> {{ objet.categorie }}</p>

        <!-- If the object is in exchange and the user is the owner, show exchange details button -->
        <template v-if="objet.enEchange">
          <button v-if="objet.idUtilisateur === utilisateurId" class="view-btn" @click="handleVoirEchange(objet)">
            Voir l&apos;échange
          </button>
        </template>

        <!-- If the object is not in exchange, provide deletion or exchange proposition actions -->
        <template v-else>
          <button v-if="objet.idUtilisateur === utilisateurId" class="delete-btn"
                  @click="props.handleSupprimerObjet(objet.id)">Supprimer l'objet
          </button>
          <button v-else class="exchange-btn" @click="handleProposerEchange(objet.id)">Proposer à l'échange</button>
        </template>

        <!-- Input field for exchange proposition when toggled -->
        <div class="input-container" v-if="objet.showInput">
          <input type="text" placeholder="Entrez votre proposition" class="exchange-input"/>
        </div>
      </div>
    </div>

    <!-- Modal section for displaying exchange details -->
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <!-- Close control for modal -->
        <span class="close" @click="showModal = false">&times;</span>
        <!-- Modal title -->
        <h2>Détails de l'échange</h2>
        <!-- Display exchange state -->
        <p><strong>État de l'échange :</strong> {{ exchangeDetails?.etatEchange }}</p>
        <!-- Exchange details grid -->
        <div class="exchange-details">
          <!-- Requested object details -->
          <div class="exchange-item">
            <h3>Objet demandé</h3>
            <p><strong>Nom :</strong> {{ exchangeDetails?.objetDemande.nom }}</p>
            <p><strong>Description :</strong> {{ exchangeDetails?.objetDemande.description }}</p>
            <p><strong>Catégorie :</strong> {{ exchangeDetails?.objetDemande.categorie }}</p>
          </div>
          <!-- Proposed object details -->
          <div class="exchange-item">
            <h3>Objet proposé</h3>
            <p><strong>Nom :</strong> {{ exchangeDetails?.objetPropose.nom }}</p>
            <p><strong>Description :</strong> {{ exchangeDetails?.objetPropose.description }}</p>
            <p><strong>Catégorie :</strong> {{ exchangeDetails?.objetPropose.categorie }}</p>
          </div>
        </div>
        <!-- Button to close modal -->
        <button class="close-btn" @click="showModal = false">Fermer</button>
      </div>
    </div>
  </section>
</template>

<style scoped>
.objet-zone {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f4f4f4;
  padding: 20px;
}

.grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  padding: 1em;
}

.card {
  background: white;
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  flex-shrink: 0;
  transition: transform 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
}

h2 {
  margin-bottom: 10px;
  font-size: 18px;
  color: #333;
}

.description {
  color: #666;
  font-size: 14px;
  margin-bottom: 8px;
}

.categorie {
  font-size: 14px;
  font-weight: bold;
  margin-top: 5px;
  color: #444;
}

.exchange-btn, .delete-btn, .view-btn {
  background: #4a90e2;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 10px;
  width: 100%;
  transition: background 0.3s ease-in-out;
}

.exchange-btn:hover, .delete-btn:hover, .view-btn:hover {
  background: #357ab8;
}

.input-container {
  margin-top: 10px;
  animation: fadeIn 0.3s ease-in-out;
}

.exchange-input {
  padding: 10px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.error {
  color: red;
  font-size: 16px;
  margin-bottom: 20px;
  text-align: center;
}

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
  text-align: center;
  width: 80%;
  max-width: 500px;
  position: relative;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  cursor: pointer;
}

.close-btn {
  background: #e74c3c;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 10px;
  width: 100%;
  transition: background 0.3s ease-in-out;
}

.close-btn:hover {
  background: #c0392b;
}

.exchange-details {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.exchange-item {
  width: 45%;
}

@media (max-width: 768px) {
  .grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 500px) {
  .card {
    min-width: 90%;
  }
}
</style>