describe('Objets Component', () => {
    beforeEach(() => {

        cy.login();  // Appel de la commande login personnalisée
        cy.visit('/');
        // Mock les appels API
        cy.intercept('GET', 'http://localhost:3000/api/utilisateurs/trans', {
            statusCode: 200,
            body: { id: 'user-123' },
        }).as('fetchUtilisateurId');

        cy.intercept('GET', 'http://localhost:3000/api/objets', {
            statusCode: 200,
            body: [
                {
                    id: 1,
                    nom: 'Objet 1',
                    description: 'Description de l\'objet 1',
                    categorie: 'Catégorie 1',
                    idUtilisateur: 'user-123',
                    showInput: false,
                    enEchange: false,
                },
                {
                    id: 2,
                    nom: 'Objet 2',
                    description: 'Description de l\'objet 2',
                    categorie: 'Catégorie 2',
                    idUtilisateur: 'user-456',
                    showInput: false,
                    enEchange: false,
                },
            ],
        }).as('fetchObjets');

        cy.intercept('GET', 'http://localhost:3000/api/echanges/all', {
            statusCode: 200,
            body: [],
        }).as('fetchEchanges');

        // Charger la page
        cy.visit('/');
    });

    it('devrait charger les objets et l\'utilisateur', () => {
        // Vérifier que l'ID utilisateur est récupéré et les objets sont affichés
        cy.wait('@fetchUtilisateurId');
        cy.wait('@fetchObjets');

        // Vérifier que les objets sont affichés
        cy.get('.card').should('have.length', 2);
        cy.get('.card').first().find('h2').should('contain', 'Objet 1');
        cy.get('.card').eq(1).find('h2').should('contain', 'Objet 2');
    });

    it('devrait afficher un message d\'erreur si une erreur se produit', () => {
        // Simuler une erreur lors de la récupération des objets
        cy.intercept('GET', 'http://localhost:3000/api/objets', {
            statusCode: 500,
            body: { message: 'Erreur lors de la récupération des objets' },
        }).as('fetchObjetsError');

        cy.visit('/');
        cy.wait('@fetchObjetsError');

        // Vérifier l'affichage du message d'erreur
        cy.get('.error').should('contain', 'Erreur lors de la récupération des objets');
    });

    it('devrait permettre de proposer un échange', () => {
        cy.wait('@fetchObjets');

        // Vérifier que le bouton "Proposer à l'échange" est présent pour l'objet dont l'utilisateur n'est pas le propriétaire
        cy.get('.card').eq(1).find('.exchange-btn').should('be.visible');
        cy.get('.card').eq(1).find('.exchange-btn').click();

        // Vérifier que le champ de proposition est visible après avoir cliqué
        cy.get('.card').eq(1).find('.input-container').should('be.visible');
    });

    it('devrait permettre de supprimer un objet', () => {
        cy.wait('@fetchObjets');

        // Vérifier que le bouton "Supprimer l'objet" est présent pour l'objet dont l'utilisateur est le propriétaire
        cy.get('.card').first().find('.delete-btn').should('be.visible');

        // Simuler le clic sur le bouton "Supprimer"
        cy.get('.card').first().find('.delete-btn').click();

        // Vérifier que l'objet a été supprimé
        cy.get('.card').should('have.length', 1);
        cy.get('.card').first().find('h2').should('not.contain', 'Objet 1');
    });

    it('devrait afficher "Déjà demandé" si l\'objet est en échange et l\'utilisateur n\'en est pas le propriétaire', () => {
        cy.intercept('GET', 'http://localhost:3000/api/echanges/all', {
            statusCode: 200,
            body: [
                { objetId: 2 },
            ],
        }).as('fetchEchangesWithData');

        cy.visit('/');
        cy.wait('@fetchEchangesWithData');

        // Vérifier que le texte "Déjà demandé" est affiché pour l'objet en échange
        cy.get('.card').eq(1).find('.info').should('contain', 'Déjà demandé');
    });
});
