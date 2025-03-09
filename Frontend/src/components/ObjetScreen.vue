<script setup lang="ts">
import {ref, onMounted} from 'vue';

const objets = ref<{
  id: number;
  nom: string;
  description: string;
  categorie: string;
  showInput: boolean;
  enEchange: boolean;
  idUtilisateur: string
}[]>([]);
const errorMessage = ref('');
const utilisateurId = ref(''); // ID réel de l'utilisateur connecté

// Récupérer l'ID de l'utilisateur connecté
const authToken: string = localStorage.getItem('authToken') || '';
const fetchUtilisateurId = async () => {
  try {
    const response = await fetch('http://localhost:3000/api/utilisateurs/trans', {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': authToken,
      },
    });
    if (!response.ok) throw new Error('Erreur lors de la récupération de lutilisateur');

    const data = await response.json();
    utilisateurId.value = data.id;
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
}
/**
 * /**
 *  *   Règles d'affichage pour un objet
 *  * Si l'objet fait partie d'un échange
 *  *
 *  * Si l'utilisateur est le propriétaire → Il peut consulter l'échange.
 *  * Si l'utilisateur n'est pas le propriétaire → Afficher "déjà demandé" (il ne peut pas proposer d'échange).
 *  * Si l'objet ne fait pas partie d'un échange
 *  *
 *  * Si l'utilisateur est le propriétaire → Il peut supprimer l'objet.
 *  * Si l'utilisateur n'est pas le propriétaire → Il peut proposer un échange.
 *  *
 *  */


// Récupérer les objets et vérifier s'ils sont en échange
const fetchObjets = async () => {
  try {
    const response = await fetch('http://localhost:3000/api/objets');
    if (!response.ok) throw new Error('Erreur lors de la récupération des objets');

    const data = await response.json();
    objets.value = data.map((objet: any) => ({
      id: objet.id,
      nom: objet.nom,
      description: objet.description,
      categorie: objet.categorie,
      idUtilisateur: objet.idUtilisateur, // Mise à jour pour correspondre aux données API
      showInput: false,
      enEchange: false, // Sera mis à jour après récupération des échanges
    }));

    await fetchEchanges();
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

// Récupérer la liste des échanges
const fetchEchanges = async () => {
  try {
    const response = await fetch('http://localhost:3000/api/echanges/all');
    if (!response.ok) throw new Error('Erreur lors de la récupération des échanges');

    const echanges = await response.json();
    objets.value.forEach(objet => {
      if (echanges.some((e: any) => e.objetId === objet.id)) {
        objet.enEchange = true;
      }
    });
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

// Fonction pour afficher/cacher l'input d'échange
const handleProposerEchange = (objetId: number) => {
  const objet = objets.value.find(o => o.id === objetId);
  if (objet) {
    objet.showInput = !objet.showInput;
  }
};

// Fonction pour supprimer un objet
const handleSupprimerObjet = async (objetId: number) => {
  try {
    await fetch(`http://localhost:3000/api/objets/${objetId}`, {method: 'DELETE'});
    objets.value = objets.value.filter(o => o.id !== objetId);
  } catch (error) {
    errorMessage.value = 'Erreur lors de la suppression';
  }
};

onMounted(async () => {
  await fetchUtilisateurId();
  await fetchObjets();
});
</script>

<template>
  <div class="container">
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

    <div class="grid">
      <div v-for="objet in objets" :key="objet.id" class="card">
        <h2>{{ objet.nom }}</h2>
        <p class="description">{{ objet.description }}</p>
        <p class="categorie"><strong>Catégorie :</strong> {{ objet.categorie }}</p>

        <template v-if="objet.enEchange">
          <button v-if="objet.idUtilisateur === utilisateurId" class="view-btn">Consulter l'échange</button>
          <p v-else class="info">Déjà demandé</p>
        </template>

        <template v-else>
          <button v-if="objet.idUtilisateur === utilisateurId" class="delete-btn"
                  @click="handleSupprimerObjet(objet.id)">Supprimer l'objet
          </button>
          <button v-else class="exchange-btn" @click="handleProposerEchange(objet.id)">Proposer à l'échange</button>
        </template>

        <div class="input-container" v-if="objet.showInput">
          <input type="text" placeholder="Entrez votre proposition" class="exchange-input"/>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Style global */
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #f4f4f4;
  padding: 20px;
}

.grid {
  display: flex;
  overflow-x: auto;
  gap: 20px;
  padding-bottom: 10px;
}

/* Style des cartes */
.card {
  background: white;
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  min-width: 280px;
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

/* Bouton d'échange */
.exchange-btn {
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

.exchange-btn:hover {
  background: #357ab8;
}

/* Input animé */
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

/* Animation d'apparition */
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

/* Message d'erreur */
.error {
  color: red;
  font-size: 16px;
  margin-bottom: 20px;
  text-align: center;
}
</style>
