<template>
  <div id="app">
    <!-- Le contenu ici changera en fonction de la route active -->
    <router-view />
  </div>
</template>

<script setup lang="ts">
// Pas besoin d'importer les composants ici, Vue Router s'en charge automatiquement.
</script>

<style scoped>
/* Style global pour l'application */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: #2c3e50;
  margin: 0;
  padding: 0;
}
</style>
