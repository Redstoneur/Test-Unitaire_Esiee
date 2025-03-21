package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.dto.ObjetDTO;
import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.repository.ObjetRepository;
import fr.knap.testunitaire_esiee.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing Objet entities.
 */
@Service
public class ObjetService {

    @Autowired
    private ObjetRepository objetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

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
    public List<ObjetDTO> obtenirTousLesObjets() {
        List<Objet> objets = objetRepository.findAll();

        List<ObjetDTO> tousLesObjets = new ArrayList<>();

        objets.forEach(o -> {
            if (o.getDateSuppression() != null) {
                return;
            }
            Utilisateur utilisateur = o.getUtilisateur();
            tousLesObjets.add(new ObjetDTO(o.getId(),o.getNom(), o.getDescription(), o.getCategorie(), utilisateur.getPseudo(), utilisateur.getId(), o.getDateCreation()));
        });

        return tousLesObjets;
    }

    /**
     * Retrieves an Objet entity by its ID.
     *
     * @param id The ID of the Objet entity.
     * @return The Objet entity with the specified ID, or null if not found.
     */
    public Objet obtenirObjetParId(Long id) {
        Objet objet = objetRepository.findById(id).orElse(null);

        if (objet != null && objet.getDateSuppression() != null) {
            return null;
        }

        return objet;
    }

    /**
     * Updates an existing Objet entity.
     *
     * @param id    The ID of the Objet entity to be updated.
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
        Optional<Objet> objetOptional = objetRepository.findById(id);
        if (objetOptional.isPresent()) {
            Objet objet = objetOptional.get();
            // Set the deletion date instead of deleting the record from the database.
            objet.setDateSuppression(LocalDateTime.now());
            objetRepository.save(objet);
        }
    }

    /**
     * Retrieves a list of Objet entities by the user ID.
     *
     * @param idUtilisateur The ID of the user.
     * @return A list of Objet entities associated with the specified user ID.
     */
    public List<ObjetDTO> obtenirObjetsParUtilisateur(Long idUtilisateur) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(idUtilisateur);

        if (utilisateur.isPresent()) {
            Utilisateur u = utilisateur.get();
            List<Objet> objets = objetRepository.findByUtilisateurId(idUtilisateur);

            // retiré les objet supprimés
            objets = objets.stream().filter(
                    o -> o.getDateSuppression() == null
            ).toList();

            return objets.stream()
                    .map(o -> new ObjetDTO(o.getId(),o.getNom(), o.getDescription(), o.getCategorie(), u.getPseudo(), u.getId(), o.getDateCreation()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Updates the objects of an exchange.
     *
     * @param echange The exchange to be updated.
     */
    public void updateExchangeObjects(Echange echange) {
        Objet objetPropose = echange.getObjetPropose();
        Objet objetDemande = echange.getObjetDemande();
        objetPropose.setUtilisateur(echange.getProprietaireObjetDemande());
        objetDemande.setUtilisateur(echange.getProprietaireObjetPropose());
        this.mettreAJourObjet(objetPropose.getId(), objetPropose);
        this.mettreAJourObjet(objetDemande.getId(), objetDemande);
    }
}