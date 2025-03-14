package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.ObjetBufferDTO;
import fr.knap.testunitaire_esiee.dto.ObjetDTO;
import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.ObjetService;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
     * @param authToken The authorization token of the user.
     * @param objetBufferDTO The data transfer object containing the details of the object to be created.
     * @return The created object.
     */
    @PostMapping
    public ObjetDTO creerObjet(@RequestHeader("Authorization") String authToken, @RequestBody ObjetBufferDTO objetBufferDTO) {
        if(!utilisateurService.verifyToken(authToken))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
        Utilisateur utilisateur = utilisateurService.obtenirUtilisateurParToken(authToken);

        Objet objet = new Objet(
                utilisateur,
                objetBufferDTO.getNom(),
                objetBufferDTO.getDescription(),
                objetBufferDTO.getCategorie(),
                objetBufferDTO.getDateCreation()
        );

        Objet objetCreated = objetService.creerObjet(objet);

        return new ObjetDTO(
                objetCreated.getId(),
                objetCreated.getNom(),
                objetCreated.getDescription(),
                objetCreated.getCategorie(),
                objetCreated.getUtilisateur().getPseudo(),
                objetCreated.getUtilisateur().getId(),
                objetCreated.getDateCreation()
        );
    }

    /**
     * Retrieves all objects.
     *
     * @return A list of all objects.
     */
    @GetMapping
    public List<ObjetDTO> obtenirTousLesObjets() {
        return objetService.obtenirTousLesObjets();
    }

    /**
     * Retrieves objects by user ID.
     *
     * @param authToken The authorization token of the user.
     * @param idUtilisateur The ID of the user whose objects are to be retrieved.
     * @return A list of objects belonging to the specified user.
     */
    @GetMapping("/{idUtilisateur}")
    public List<ObjetDTO> obtenirObjetsParUtilisateur(@RequestHeader("Authorization") String authToken, @PathVariable Long idUtilisateur) {
        if(utilisateurService.verifyToken(authToken))
            return objetService.obtenirObjetsParUtilisateur(idUtilisateur);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
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
     * @param authToken The authorization token of the user.
     * @param id The ID of the object to update.
     * @param objet The updated object data.
     * @return The updated object.
     */
    @PutMapping("/{id}")
    public Objet mettreAJourObjet(@RequestHeader("Authorization") String authToken, @PathVariable Long id, @RequestBody Objet objet) {
        if (utilisateurService.verifyToken(authToken)) {
            Objet objetV = objetService.obtenirObjetParId(id);
            if (objetV != null && objetV.getDateSuppression() != null)
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Object is deleted");

            return objetService.mettreAJourObjet(id, objet);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }

    /**
     * Deletes an object by its ID.
     *
     * @param authToken The authorization token of the user.
     * @param id The ID of the object to delete.
     */
    @DeleteMapping("/{id}")
    public void supprimerObjet(@RequestHeader("Authorization") String authToken, @PathVariable Long id) {
        if(utilisateurService.verifyToken(authToken)){
            objetService.supprimerObjet(id);
            throw new ResponseStatusException(HttpStatus.OK, "Object deleted");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }

}