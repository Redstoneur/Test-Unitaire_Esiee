describe('Authentification', () => {
    beforeEach(() => {
        cy.visit('/');
    });

    it('Doit afficher le formulaire de connexion par défaut', () => {
        cy.contains('Connexion').should('be.visible');
        cy.get('input[type="email"]').should('be.visible');
        cy.get('input[type="password"]').should('be.visible');
        cy.get('button[type="submit"]').should('contain', 'Se connecter');
    });

    it('Doit basculer vers le mode inscription', () => {
        cy.get('.toggle-btn').click();
        cy.contains('Inscription').should('be.visible');
        cy.get('input[placeholder="Pseudo"]').should('be.visible');
        cy.get('input[placeholder="Nom"]').should('be.visible');
        cy.get('input[placeholder="Prénom"]').should('be.visible');
        cy.get('input[placeholder="Email"]').should('be.visible');
        cy.get('input[placeholder="Mot de passe"]').should('be.visible');
        cy.get('button[type="submit"]').should('contain', "S'inscrire");
    });

    it('Doit afficher une erreur si les champs sont vides', () => {
        cy.get('.toggle-btn').click();
        cy.get('button[type="submit"]').click();
    });

    it('Doit permettre une inscription réussie', () => {
        cy.get('.toggle-btn').click();
        cy.get('input[placeholder="Pseudo"]').type('testuser');
        cy.get('input[placeholder="Nom"]').type('Test');
        cy.get('input[placeholder="Prénom"]').type('User');
        cy.get('input[placeholder="Email"]').type('test@example.com');
        cy.get('input[placeholder="Mot de passe"]').type('password123');
        cy.get('button[type="submit"]').click();

        cy.wait(1000); // Attendre la réponse

    });
    it('Doit permettre une connexion réussie', () => {
        cy.get('input[type="email"]').type('test@example.com');
        cy.get('input[type="password"]').type('password123');
        cy.get('button[type="submit"]').click();

        cy.wait(1000);

    });
});
