
// cypress/support/commands.d.ts
declare namespace Cypress {
    interface Chainable {
        login(): Chainable<void>; // Déclare la commande `login`
    }
}

// Commande personnalisée pour simuler une connexion
Cypress.Commands.add('login', () => {
    // Simuler la connexion en stockant un jeton d'authentification dans le localStorage a remplacer par le votre
    localStorage.setItem('authToken', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YW1hMkB5YWhvby5mciMxMjM0NTYiLCJpYXQiOjE3NDE1NDg5NzcsImV4cCI6MTc0MTU1MjU3N30.JLaf970HwvuG3l4QgK57IM1zto_J1eiWHu61S-Z7F_8'); // Remplacer par un jeton valide si nécessaire
});


