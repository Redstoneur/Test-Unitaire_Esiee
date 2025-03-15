describe('Ajout d\'objet avec authentification', () => {
    beforeEach(() => {
        cy.login();  // Appel de la commande login personnalisée
        cy.visit('http://localhost:5173/add-object');
    });

    it('Doit afficher le formulaire d\'ajout d\'objet pour un utilisateur authentifié', () => {
        cy.contains('Ajouter un Objet').should('be.visible');
        cy.get('input#nomObjet').should('be.visible');
        cy.get('textarea#descriptionObjet').should('be.visible');
        cy.get('select#categorieObjet').should('be.visible');
        cy.get('button[type="submit"]').should('contain', 'Ajouter l\'Objet');
    });

    it('Doit afficher une erreur si les champs sont vides', () => {
        // Soumettre le formulaire sans rien remplir
        cy.get('button[type="submit"]').click();
    });

    it('Doit gérer l\'erreur en cas d\'échec de l\'ajout de l\'objet', () => {
        // Simuler une erreur d'API (mauvaise réponse)
        cy.intercept('POST', 'http://localhost:3000/api/objets', {
            statusCode: 500,
            body: {message: 'Erreur lors de l\'ajout de l\'objet'}
        });

        // Remplir le formulaire avec des données valides
        cy.get('input#nomObjet').type('Chaise');
        cy.get('textarea#descriptionObjet').type('Une chaise confortable');
        cy.get('select#categorieObjet').select('MOBILIER');

        // Soumettre le formulaire
        cy.get('button[type="submit"]').click();

        // Vérifier que l'erreur est affichée
        cy.contains('Erreur lors de l\'ajout de l\'objet').should('be.visible');
    });


    it('should add an object for each category using addObject command', () => {
        const objects = [
            {nom: 'Table', description: 'Une table en bois', categorie: 'MOBILIER'},
            {nom: 'Tondeuse', description: 'Une tondeuse électrique', categorie: 'JARDINAGE'},
            {nom: 'Ordinateur', description: 'Un ordinateur portable', categorie: 'INFORMATIQUE'},
            {nom: 'Manette', description: 'Une manette de jeu', categorie: 'GAMING'},
            {nom: 'Perceuse', description: 'Une perceuse sans fil', categorie: 'OUTILS'},
            {nom: 'Timbres', description: 'Une collection de timbres', categorie: 'COLLECTION'},
            {nom: 'Livre', description: 'Un roman policier', categorie: 'LITTERATURE'},
            {nom: 'Chemise', description: 'Une chemise en coton', categorie: 'VETEMENTS'},
            {nom: 'Machine à laver', description: 'Une machine à laver 8kg', categorie: 'ELECTROMENAGER'},
            {nom: 'Objet', description: 'Un objet non classé', categorie: 'AUTRE'}
        ];

        objects.forEach((objet) => {
            cy.testAddObject(objet.nom, objet.description, objet.categorie);
        });
    });


    it('Doit rediriger vers la page de connexion si l\'utilisateur n\'est pas connecté', () => {
        // Supprimer le token d'authentification pour simuler un utilisateur non connecté
        localStorage.removeItem('authToken');

        // Visiter la page d'ajout d'objet
        cy.visit('http://localhost:5173/add-object');
    });
});
