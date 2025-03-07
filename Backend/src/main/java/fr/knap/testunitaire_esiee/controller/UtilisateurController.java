package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.UtilisateurDTO;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurService.obtenirTousLesUtilisateurs();
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/{id}")
    public UtilisateurDTO obtenirUtilisateurInfoParId(@PathVariable Long id) {
        return utilisateurService.obtenirUtilisateurInfoParId(id);
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/{id}")
    public UtilisateurDTO obtenirUtilisateurPseudoParId(@PathVariable Long id) {
        return utilisateurService.obtenirUtilisateurPseudoParId(id);
    }

    /**
     * Updates an existing user.
     *
     * @param id The ID of the user to update.
     * @param utilisateur The updated user data.
     * @return The updated user.
     */
    @PutMapping("/{id}")
    public Utilisateur mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.mettreAJourUtilisateur(id, utilisateur);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }
}