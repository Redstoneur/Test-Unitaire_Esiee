import { defineConfig } from 'cypress';

export default defineConfig({
  projectId: 'y3hntm',
  e2e: {
    baseUrl: 'http://localhost:5173', // Mets ici l'URL de ton frontend Vue
  },
});

