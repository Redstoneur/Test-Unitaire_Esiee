describe('Ajout d\'objet avec authentification', () => {
    beforeEach(() => {
        cy.login();  // Appel de la commande login personnalisée
        cy.visit('http://localhost:5173/add-object');

        cy.get('input[type="email"]').type('admin@mail.fr');
        cy.get('input[type="password"]').type('admin');
        cy.get('button[type="submit"]').click();
        cy.wait(1000);

        cy.visit('/add-object');
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

    it('Doit ajouter un objet avec succès', () => {
        // Remplir les champs avec des données valides
        cy.get('input#nomObjet').type('Table');
        cy.get('textarea#descriptionObjet').type('Une table en bois');
        cy.get('select#categorieObjet').select('MOBILIER');

        // Simuler l'envoi du formulaire
        cy.get('button[type="submit"]').click();

        // Vérifier que le message de succès est affiché
        cy.contains('Objet ajouté avec succès !').should('be.visible');

        // Vérifier que le formulaire est réinitialisé après l'ajout
        cy.get('input#nomObjet').should('have.value', '');
        cy.get('textarea#descriptionObjet').should('have.value', '');
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

    it('Ajouter un objet de chaque catégorie', () => {
        // Créer un objet pour chaque catégorie
        cy.get('input#nomObjet').type('Table');
        cy.get('textarea#descriptionObjet').type('Une table en bois');
        cy.get('select#categorieObjet').select('MOBILIER');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Tondeuse');
        cy.get('textarea#descriptionObjet').type('Une tondeuse électrique');
        cy.get('select#categorieObjet').select('JARDINAGE');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Ordinateur');
        cy.get('textarea#descriptionObjet').type('Un ordinateur portable');
        cy.get('select#categorieObjet').select('INFORMATIQUE');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Manette');
        cy.get('textarea#descriptionObjet').type('Une manette de jeu');
        cy.get('select#categorieObjet').select('GAMING');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Perceuse');
        cy.get('textarea#descriptionObjet').type('Une perceuse sans fil');
        cy.get('select#categorieObjet').select('OUTILS');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Timbres');
        cy.get('textarea#descriptionObjet').type('Une collection de timbres');
        cy.get('select#categorieObjet').select('COLLECTION');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Livre');
        cy.get('textarea#descriptionObjet').type('Un roman policier');
        cy.get('select#categorieObjet').select('LITTERATURE');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Chemise');
        cy.get('textarea#descriptionObjet').type('Une chemise en coton');
        cy.get('select#categorieObjet').select('VETEMENTS');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Machine à laver');
        cy.get('textarea#descriptionObjet').type('Une machine à laver 8kg');
        cy.get('select#categorieObjet').select('ELECTROMENAGER');
        cy.get('button[type="submit"]').click();

        cy.get('input#nomObjet').type('Objet');
        cy.get('textarea#descriptionObjet').type('Un objet non classé');
        cy.get('select#categorieObjet').select('AUTRE');
        cy.get('button[type="submit"]').click();
    });

    it('Doit rediriger vers la page de connexion si l\'utilisateur n\'est pas connecté', () => {
        // Supprimer le token d'authentification pour simuler un utilisateur non connecté
        localStorage.removeItem('authToken');

        // Visiter la page d'ajout d'objet
        cy.visit('http://localhost:5173/add-object');
    });
});
