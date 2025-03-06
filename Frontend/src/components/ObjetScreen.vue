<script setup lang="ts">
import { ref, onMounted } from 'vue';

const objets = ref<{ nom: string; description: string; categorie: string; showInput: boolean }[]>([]);
const errorMessage = ref('');

// Fonction pour récupérer les objets
const fetchObjets = async () => {
  try {
    const response = await fetch('http://localhost:3000/api/objets');
    if (!response.ok) throw new Error('Erreur lors de la récupération des objets');

    // Ajouter une propriété showInput pour chaque objet pour contrôler l'affichage de l'input
    const data = await response.json();
    objets.value = data.map((objet: any) => ({
      ...objet,
      showInput: false, // Par défaut, l'input est caché
    }));
  } catch (error) {
    errorMessage.value = (error as Error).message;
  }
};

// Fonction pour alterner l'affichage de l'input pour un objet spécifique
const handleProposerEchange = (objetNom: string) => {
  const objet = objets.value.find(o => o.categorie === objetNom);
  if (objet) {
    objet.showInput = !objet.showInput; // Inverse l'état de l'input pour cet objet
  }
};

onMounted(fetchObjets);
</script>

<template>
  <div class="container">
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>

    <div class="grid">
      <div v-for="objet in objets" :key="objet.nom" class="card">
        <h2>{{ objet.nom }}</h2>
        <p class="description">{{ objet.description }}</p>
        <p class="categorie"><strong>Catégorie :</strong> {{ objet.categorie }}</p>

        <!-- Bouton pour proposer l'échange -->
        <button class="exchange-btn" @click="handleProposerEchange(objet.categorie)">Proposer à l'échange</button>

        <!-- Input qui s'affiche quand on clique sur le bouton -->
        <div v-if="objet.showInput">
          <input
              type="text"
              placeholder="Entrez votre proposition"
              class="exchange-input"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 800px;
  margin: 20px auto;
  text-align: center;
}

.grid {
  display: flex;
  overflow-x: auto; /* Permet le défilement horizontal */
  gap: 20px;
  padding-bottom: 10px;
}

.card {
  background: white;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: left;
  min-width: 250px;
  flex-shrink: 0;
}

h2 {
  margin-bottom: 10px;
  color: #333;
}

.description {
  color: #666;
  font-size: 14px;
}

.categorie {
  font-size: 14px;
  margin-top: 5px;
}

.exchange-btn {
  background: #667eea;
  color: white;
  padding: 8px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 10px;
  width: 100%;
}
.exchange-btn:hover {
  background: #5a67d8;
}

.exchange-input {
  margin-top: 10px;
  padding: 8px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

.error {
  color: red;
  margin-bottom: 20px;
}
</style>
