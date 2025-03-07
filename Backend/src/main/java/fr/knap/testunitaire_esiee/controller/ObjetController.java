package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.model.ObjetBuffer;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.ObjetService;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing objects.
 */
@RestController
@RequestMapping("/api/objets")
public class ObjetController {

    @Autowired
    private ObjetService objetService;

    @Autowired
    private UtilisateurService utilisateurService;


    /**
     * Creates a new object.
     *
     * @param objet The object to be created.
     * @return The created object.
     */
    @PostMapping
    public Objet creerObjet(@RequestHeader("Authorization") String authToken, @RequestBody ObjetBuffer objetBuffer) {
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParToken(authToken);

        Objet objet = new Objet(
                utilisateur,
                objetBuffer.getNom(),
                objetBuffer.getDescription(),
                objetBuffer.getCategorie(),
                objetBuffer.getDateCreation()
        );
        return objetService.creerObjet(objet);
    }

    /**
     * Retrieves all objects.
     *
     * @return A list of all objects.
     */
    @GetMapping
    public List<Objet> obtenirTousLesObjets() {
        return objetService.obtenirTousLesObjets();
    }

    /**
     * Retrieves objects by user ID.
     *
     * @param idUtilisateur The ID of the user whose objects are to be retrieved.
     * @return A list of objects belonging to the specified user.
     */
    @GetMapping("/{idUtilisateur}")
    public List<Objet> obtenirObjetsParUtilisateur(@PathVariable Long idUtilisateur) {
        return objetService.obtenirObjetsParUtilisateur(idUtilisateur);
    }

    /**
     * Retrieves a specific object by its ID.
     *
     * @param id The ID of the object to retrieve.
     * @return The object with the specified ID.
     */
    @GetMapping("/{id}")
    public Objet obtenirObjetParId(@PathVariable Long id) {
        return objetService.obtenirObjetParId(id);
    }

    /**
     * Updates an existing object.
     *
     * @param id    The ID of the object to update.
     * @param objet The updated object data.
     * @return The updated object.
     */
    @PutMapping("/{id}")
    public Objet mettreAJourObjet(@PathVariable Long id, @RequestBody Objet objet) {
        return objetService.mettreAJourObjet(id, objet);
    }

    /**
     * Deletes an object by its ID.
     *
     * @param id The ID of the object to delete.
     */
    @DeleteMapping("/{id}")
    public void supprimerObjet(@PathVariable Long id) {
        objetService.supprimerObjet(id);
    }

}