<script setup lang="ts">
import {defineEmits, defineProps} from 'vue';

const props = defineProps({
  title: {type: String, required: true},
  subtitle: {type: String, default: ''},
  routerLinkLabel: {type: String, default: ''},
  routerLinkTarget: {type: String, default: ''},
  showLogout: {type: Boolean, default: false}
});

const emit = defineEmits(['logout']);

const onLogout = () => {
  emit('logout');
};
</script>

<template>
  <header>
    <div class="header-titles">
      <h2>{{ props.title }}</h2>
      <h3 v-if="props.subtitle" class="objet-title">{{ props.subtitle }}</h3>
    </div>
    <div class="header-actions">
      <router-link
          v-if="props.routerLinkTarget"
          :to="props.routerLinkTarget"
          class="header-link"
      >
        {{ props.routerLinkLabel }}
      </router-link>
      <button v-if="props.showLogout" class="disconected" @click="onLogout">
        Déconnexion
      </button>
    </div>
  </header>
</template>

<style scoped>
header {
  margin-bottom: 1em;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header-titles h2,
.header-titles h3 {
  margin: 0 0 1em;
}

.objet-title {
  font-size: 1rem;
}

.header-link {
  margin-right: 10px;
  text-decoration: none;
  color: #667eea;
}

.disconected {
  margin-left: 20px;
  transition: 0.5s ease-in-out;
}

.disconected:hover {
  transition: background-color 0.3s ease-in-out;
}

button {
  background: #667eea;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background: #5a67d8;
}


</style>