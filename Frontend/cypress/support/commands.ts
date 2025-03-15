
// cypress/support/commands.d.ts
declare namespace Cypress {
    interface Chainable {
        login(): Chainable<void>; // Déclare la commande `login`
        testAddObject(nomObjet: string, descriptionObjet: string,
                      categorieObjet: string): Chainable<void>; // Déclare la commande `addObject`
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

// Commande personnalisée l'ajoût d'un objet
Cypress.Commands.add('testAddObject', (nomObjet: string, descriptionObjet: string, categorieObjet: string) => {
    cy.get('input#nomObjet').type(nomObjet);
    cy.get('textarea#descriptionObjet').type(descriptionObjet);
    cy.get('select#categorieObjet').select(categorieObjet);
    cy.get('button[type="submit"]').click();
    cy.contains('Objet ajouté avec succès !').should('be.visible');
    cy.get('button.continue-btn').click();
    cy.get('input#nomObjet').should('have.value', '');
    cy.get('textarea#descriptionObjet').should('have.value', '');
});


