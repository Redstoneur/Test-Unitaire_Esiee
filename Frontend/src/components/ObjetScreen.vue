<!--
  @file Frontend/src/components/ObjetScreen.vue
  @description ObjetScreen component handles the display of objects.
  It supports showing object details, proposing exchanges, deleting objects and viewing exchange details via a modal.
-->
<script setup lang="ts">
import {computed, onBeforeUnmount, onMounted, Ref, ref} from 'vue';

// Import Component
import ObjetForm from "./ObjetForm.vue";

// Import function
import {CreateEchange, GetEchange, UpdateEchange, UserInformation} from "../Function/ApiRequest";

// Import types
import Objet from "../Types/Objet";
import EtatEchange from "../Types/EtatEchange";

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
 * Controls the visibility of the proposal exchange modal.
 * @type {Ref<boolean>}
 */
const showProposeModal: Ref<boolean> = ref(false);

/**
 * Controls whether to display the creation form inside the proposal exchange modal.
 * @type {Ref<boolean>}
 */
const showCreateForm: Ref<boolean> = ref(false);

/**
 * Holds the selected user object (either chosen from the list or created via form).
 * @type {Ref<number | null>}
 */
const selectedObjetProposal: Ref<number | null> = ref<number | null>(null);

/**
 * Holds the object on which the user clicked "proposer à l'échange".
 * @type {Ref<number | null>}
 */
const objetRecherche: Ref<number | null> = ref<number | null>(null);

/**
 * Holds the name of the object to be created.
 * @type {Ref<string>}
 */
const nomObjet: Ref<string> = ref('');

/**
 * Holds the description of the object to be created.
 * @type {Ref<string>}
 */
const descriptionObjet: Ref<string> = ref('');

/**
 * Holds the category of the object to be created.
 * @type {Ref<string>}
 */
const categorieObjet: Ref<string> = ref('');

/**
 * Filter user's objects not in exchange.
 */
const userObjects = computed(() => {
  return props.objets.filter(o => o.idUtilisateur === utilisateurId.value && !o.enEchange);
});

/**
 * Items per page based on screen width.
 * @type {Ref<number>}
 */
const itemsPerPage: Ref<number> = ref(window.innerWidth < 480 ? 1 : 4);

/**
 * Updates itemsPerPage on window resize.
 */
const updateItemsPerPage = () => {
  itemsPerPage.value = window.innerWidth < 480 ? 1 : 4;
};

/**
 * Current page for pagination.
 * @type {Ref<number>}
 */
const currentPage: Ref<number> = ref(1);


/**
 * Computes a paginated subset of user's objects.
 * Calculates the starting index and returns a slice of the user objects corresponding to the current page.
 */
const paginatedUserObjects = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  return userObjects.value.slice(start, start + itemsPerPage.value);
});

/**
 * Advances to the next page if there are more objects.
 */
const nextPage = () => {
  if (currentPage.value * itemsPerPage.value < userObjects.value.length) {
    currentPage.value++;
  }
};

/**
 * Goes back to the previous page if not on the first page.
 */
const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

/**
 * Fetches the logged-in user's id by making an asynchronous request to the UserInformation API.
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
 * Handles the request to view exchange details.
 * @param objet - The object for which exchange details are to be shown.
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
 * Handles opening the proposal exchange modal.
 * Stores the target object (to be acquired) and opens the modal.
 * @param objet - The object for which the exchange is proposed.
 */
const handleProposerExchange = (objet: Objet) => {
  objetRecherche.value = objet.id;
  // Reset any previous selection and creation form state
  selectedObjetProposal.value = null;
  showCreateForm.value = false;
  showProposeModal.value = true;
};

/**
 * Sets the selected proposed object from the user's list.
 * @param objet - The user object selected for exchange proposition.
 */
const selectUserObjet = (objet: Objet) => {
  selectedObjetProposal.value = objet.id;
  // Immediately confirm the exchange upon selection
  confirmExchange();
};

/**
 * Called when a new object is created via the form.
 * @param payload - Contains the created object.
 */
const handleObjetCreated = (payload: { id: number }) => {
  selectedObjetProposal.value = payload.id
  // Optionally hide the creation form once an object is created.
  showCreateForm.value = false;
  // Immediately confirm the exchange after object creation
  confirmExchange();
};

/**
 * Confirms and creates the exchange by calling the API.
 * @async
 */
const confirmExchange = async () => {
  if (!selectedObjetProposal.value || !objetRecherche.value) return;

  const response = await CreateEchange(
      selectedObjetProposal.value,
      objetRecherche.value,
      authToken
  );
  if (response instanceof Error || !response.ok) {
    errorMessage.value = 'Erreur lors de la création de l\'échange';
    return;
  }
  // Reset modal state on success
  showProposeModal.value = false;
  errorMessage.value = '';

  // recharge la page pour afficher les changements
  location.reload();
};

const handleUpdateExchange = async (nouvelEtat: EtatEchange) => {
  if (!exchangeDetails.value || !exchangeDetails.value.id) return;

  const response = await UpdateEchange(
      exchangeDetails.value.id,
      nouvelEtat,
      authToken
  );
  if (response instanceof Error || !response.ok) {
    errorMessage.value = 'Erreur lors de la mise à jour de l\'échange';
    return;
  }
  showModal.value = false;
  errorMessage.value = '';
  location.reload();
};

/**
 * Component mounted lifecycle hook.
 * Fetches the logged-in user's ID when the component is mounted.
 */
onMounted(async () => {
  await fetchUtilisateurId();
  window.addEventListener('resize', updateItemsPerPage);
});

/**
 * Removes the resize event listener when the component is about to unmount.
 */
onBeforeUnmount(() => {
  window.removeEventListener('resize', updateItemsPerPage);
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
        <!-- Display object owner -->
        <p class="owner"><strong>Propriétaire :</strong> {{ objet.utilisateur }}</p>

        <!-- If the object is in exchange and the user is the owner, show exchange details button -->
        <template v-if="objet.enEchange">
          <button v-if="objet.idUtilisateur === utilisateurId" class="view-btn" @click="handleVoirEchange(objet)">
            Voir l&apos;échange
          </button>
        </template>

        <!-- If the object is not in exchange, provide deletion or exchange proposition actions -->
        <template v-else>
          <button v-if="objet.idUtilisateur === utilisateurId" class="delete-btn"
                  @click="props.handleSupprimerObjet(objet.id)">
            Supprimer l&apos;objet
          </button>
          <!-- Modified button logic: open propose exchange modal -->
          <button v-else class="exchange-btn" @click="handleProposerExchange(objet)">
            Proposer à l&apos;échange
          </button>
        </template>

        <!-- Input field for exchange proposition when toggled -->
        <div class="input-container" v-if="objet.showInput">
          <input type="text" placeholder="Entrez votre proposition" class="exchange-input"/>
        </div>
      </div>
    </div>

    <!-- Exchange details modal (existing) -->
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <!-- Close control for modal -->
        <span class="close" @click="showModal = false">&times;</span>
        <!-- Modal title -->
        <h2>Détails de l&apos;échange</h2>
        <!-- Display exchange state -->
        <p><strong>État de l&apos;échange :</strong> {{ exchangeDetails?.etatEchange }}</p>
        <!-- Exchange details grid -->
        <div class="exchange-details">
          <!-- Requested object details -->
          <div class="exchange-item">
            <h3>Objet demandé</h3>
            <p><strong>Nom :</strong> {{ exchangeDetails?.objetDemande.nom }}</p>
            <p><strong>Description :</strong> {{ exchangeDetails?.objetDemande.description }}</p>
            <p><strong>Catégorie :</strong> {{ exchangeDetails?.objetDemande.categorie }}</p>
            <p><strong>Propriétaire :</strong> {{ exchangeDetails?.objetDemande.utilisateur.pseudo}}</p>
          </div>
          <!-- Proposed object details -->
          <div class="exchange-item">
            <h3>Objet proposé</h3>
            <p><strong>Nom :</strong> {{ exchangeDetails?.objetPropose.nom }}</p>
            <p><strong>Description :</strong> {{ exchangeDetails?.objetPropose.description }}</p>
            <p><strong>Catégorie :</strong> {{ exchangeDetails?.objetPropose.categorie }}</p>
            <p><strong>Propriétaire :</strong> {{ exchangeDetails?.objetPropose.utilisateur.pseudo}}</p>
          </div>
        </div>
        <!-- In the modal in Frontend/src/components/ObjetScreen.vue -->
        <div v-if="exchangeDetails?.etatEchange === EtatEchange.ATTENTE">
          <template v-if="exchangeDetails?.objetDemande.utilisateur.id === utilisateurId">
            <button class="modal-btn" @click="handleUpdateExchange(EtatEchange.ACCEPTE)">
              Accepter
            </button>
            <button class="modal-btn" @click="handleUpdateExchange(EtatEchange.REFUSE)">
              Refuser
            </button>
          </template>
          <template v-else-if="exchangeDetails?.objetPropose.utilisateur.id === utilisateurId">
            <button class="modal-btn" @click="handleUpdateExchange(EtatEchange.ANNULER)">
              Annuler
            </button>
          </template>
        </div>
        <!-- Button to close modal -->
        <button class="close-btn" @click="showModal = false">Fermer</button>
      </div>
    </div>

    <!-- Modal for proposing an exchange -->
    <div v-if="showProposeModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showProposeModal = false">&times;</span>
        <h2>Proposer un échange</h2>
        <!-- Option to choose among user's objects -->
        <div v-if="!showCreateForm">
          <h3>Choisir un objet parmi les vôtres</h3>
          <div v-if="paginatedUserObjects.length" class="card-grid">
            <div v-for="objet in paginatedUserObjects" :key="objet.id" class="card"
                 :class="{ selected: selectedObjetProposal && selectedObjetProposal === objet.id }"
                 @click="selectUserObjet(objet)">
              <h2>{{ objet.nom }}</h2>
              <p>{{ objet.description }}</p>
              <p><strong>Catégorie :</strong> {{ objet.categorie }}</p>
            </div>
          </div>
          <p v-else>Aucun objet trouvé.</p>
          <!-- Pagination controls -->
          <div class="pagination">
            <button class="modal-btn" @click="prevPage" :disabled="currentPage === 1">
              Previous
            </button>
            <button class="modal-btn" @click="nextPage" :disabled="currentPage * itemsPerPage >= userObjects.length">
              Next
            </button>
          </div>
          <button class="modal-btn" @click="showCreateForm = true">
            Créer un nouvel objet
          </button>
        </div>
        <!-- Option to create a new object -->
        <div v-else>
          <h3>Créer un nouvel objet</h3>
          <!-- Using the existing ObjetForm component -->
          <ObjetForm
              v-model:nomObjet="nomObjet"
              v-model:descriptionObjet="descriptionObjet"
              v-model:categorieObjet="categorieObjet"
              :authToken="authToken"
              @object-added-id="handleObjetCreated"
          />
          <button class="modal-btn" @click="showCreateForm = false">
            Choisir parmi mes objets
          </button>
        </div>
        <!-- Cancel button to close modal without validating -->
        <button class="close-btn" @click="showProposeModal = false">
          Annuler
        </button>
      </div>
    </div>
  </section>
</template>

<style scoped>
/* Existing styles remain unchanged */
.objet-zone {
  display: flex;
  flex-direction: column;
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
  cursor: pointer;
}

.card:hover {
  transform: translateY(-5px);
}

.selected {
  border: 2px solid #4caf50;
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

.owner {
  font-size: 14px;
  margin-top: 5px;
  color: #444;
}

.exchange-btn, .delete-btn, .view-btn, .modal-btn {
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

.exchange-btn:hover, .delete-btn:hover, .view-btn:hover, .modal-btn:hover {
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

.card-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

@media (max-width: 480px) {
  .card-grid {
    grid-template-columns: 1fr;
  }
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

.pagination {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.pagination button {
  margin-left: 1em;
  margin-right: 1em;
}

</style>