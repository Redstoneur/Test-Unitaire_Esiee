
// cypress/support/commands.d.ts
declare namespace Cypress {
    interface Chainable {
        login(): Chainable<void>; // Déclare la commande `login`
    }
}

// Commande personnalisée pour simuler une connexion
Cypress.Commands.add('login', () => {
    cy.visit('http://localhost:5173/add-object');

    cy.get('input[type="email"]').type('admin@mail.fr');
    cy.get('input[type="password"]').type('admin');
    cy.get('button[type="submit"]').click();
    cy.wait(1000);

    cy.wait(1000);
});


