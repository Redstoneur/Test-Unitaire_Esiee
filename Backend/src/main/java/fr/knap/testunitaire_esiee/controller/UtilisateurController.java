package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.UtilisateurDTO;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
     * @param authToken The token of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/trans")
    public UtilisateurDTO obtenirUtilisateurInfoParId(@RequestHeader("Authorization") String authToken) {
        if (utilisateurService.verifyToken(authToken))
            return utilisateurService.obtenirUtilisateurInfoParToken(authToken);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param authToken The token of the user to retrieve.
     * @param id        The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/{id}")
    public Utilisateur obtenirUtilisateurParId(@RequestHeader("Authorization") String authToken, @PathVariable Long id) {
        if (utilisateurService.verifyToken(authToken))
            return utilisateurService.obtenirUtilisateurParId(id);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param authToken The token of the user to retrieve.
     * @param id        The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/pseudo/{id}")
    public UtilisateurDTO obtenirUtilisateurPseudoParId(@RequestHeader("Authorization") String authToken, @PathVariable Long id) {
        if (utilisateurService.verifyToken(authToken))
            return utilisateurService.obtenirUtilisateurPseudoParId(id);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }

    /**
     * Updates an existing user.
     *
     * @param authToken   The token of the user to update.
     * @param id          The ID of the user to update.
     * @param utilisateur The updated user data.
     * @return The updated user.
     */
    @PutMapping("/{id}")
    public Utilisateur mettreAJourUtilisateur(@RequestHeader("Authorization") String authToken, @PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        if (utilisateurService.verifyToken(authToken))
            return utilisateurService.mettreAJourUtilisateur(id, utilisateur);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }

    /**
     * Deletes a user by their ID.
     *
     * @param authToken The token of the user to delete.
     * @param id        The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@RequestHeader("Authorization") String authToken, @PathVariable Long id) {
        if (utilisateurService.verifyToken(authToken)){
            utilisateurService.supprimerUtilisateur(id);
            throw new ResponseStatusException(HttpStatus.OK, "User deleted");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid token");
    }
}