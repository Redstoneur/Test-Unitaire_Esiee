<!--
  @file Frontend/src/components/AppHeader.vue
  @description A Vue component that displays the header section of the application.
  It includes title, optional subtitle, router link for navigation, and a logout button when enabled.
-->

<script setup lang="ts">
import {defineEmits, defineProps} from 'vue';

/**
 * Defines the properties accepted by the AppHeader component.
 * @property {string} title - The main title to be displayed.
 * @property {string} [subtitle] - An optional subtitle.
 * @property {string} [routerLinkLabel] - Label for the navigation link.
 * @property {string} [routerLinkTarget] - Destination route for the navigation link.
 * @property {boolean} [showLogout] - Flag to conditionally display the logout button.
 */
const props = defineProps({
  title: {type: String, required: true},
  subtitle: {type: String, default: ''},
  routerLinkLabel: {type: String, default: ''},
  routerLinkTarget: {type: String, default: ''},
  showLogout: {type: Boolean, default: false}
});

/**
 * Event emitter for handling logout events.
 */
const emit = defineEmits(['logout']);

/**
 * Handles the logout action by emitting the 'logout' event.
 */
const onLogout = () => {
  emit('logout');
};
</script>

<template>
  <header>
    <div class="header-titles">
      <!-- Component title -->
      <h2>{{ props.title }}</h2>
      <!-- Optional subtitle display -->
      <h3 v-if="props.subtitle" class="objet-title">{{ props.subtitle }}</h3>
    </div>
    <div class="header-actions">
      <!-- Router link for navigation if a target is provided -->
      <router-link
          v-if="props.routerLinkTarget"
          :to="props.routerLinkTarget"
          class="header-link"
      >
        {{ props.routerLinkLabel }}
      </router-link>
      <!-- Logout button displayed conditionally -->
      <button v-if="props.showLogout" class="disconected" @click="onLogout">
        Déconnexion
      </button>
    </div>
  </header>
</template>

<style scoped>
/* Header container styling */
header {
  margin-bottom: 1em;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Titles style for header */
.header-titles h2,
.header-titles h3 {
  margin: 0 0 1em;
}

/* Style for the optional subtitle */
.objet-title {
  font-size: 1rem;
}

/* Styling for the navigation link */
.header-link {
  margin-right: 10px;
  text-decoration: none;
  color: #667eea;
}

/* Style for the logout button */
.disconected {
  margin-left: 20px;
  transition: 0.5s ease-in-out;
}

/* Hover effect for the logout button */
.disconected:hover {
  transition: background-color 0.3s ease-in-out;
}

/* Base button styling */
button {
  background: #667eea;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

/* Hover effect for buttons */
button:hover {
  background: #5a67d8;
}
</style>
