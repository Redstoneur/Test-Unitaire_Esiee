package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.respository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Objet entities.
 */
@Service
public class ObjetService {
    @Autowired
    private ObjetRepository objetRepository;

    /**
     * Creates a new Objet.
     *
     * @param objet The Objet entity to be created.
     * @return The created Objet entity.
     */
    public Objet creerObjet(Objet objet) {
        return objetRepository.save(objet);
    }

    /**
     * Retrieves all Objet entities.
     *
     * @return A list of all Objet entities.
     */
    public List<Objet> obtenirTousLesObjets() {
        return objetRepository.findAll();
    }

    /**
     * Retrieves an Objet entity by its ID.
     *
     * @param id The ID of the Objet entity.
     * @return The Objet entity with the specified ID, or null if not found.
     */
    public Objet obtenirObjetParId(Long id) {
        return objetRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing Objet entity.
     *
     * @param id The ID of the Objet entity to be updated.
     * @param objet The Objet entity with updated information.
     * @return The updated Objet entity, or null if the entity does not exist.
     */
    public Objet mettreAJourObjet(Long id, Objet objet) {
        if (objetRepository.existsById(id)) {
            return objetRepository.save(objet);
        }
        return null;
    }

    /**
     * Deletes an Objet entity by its ID.
     *
     * @param id The ID of the Objet entity to be deleted.
     */
    public void supprimerObjet(Long id) {
        objetRepository.deleteById(id);
    }

    /**
     * Retrieves a list of Objet entities by the user ID.
     *
     * @param idUtilisateur The ID of the user.
     * @return A list of Objet entities associated with the specified user ID.
     */
    public List<Objet> obtenirObjetsParUtilisateur(Long idUtilisateur) {
        return objetRepository.findByUtilisateurId(idUtilisateur);
    }
}