package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.respository.EchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing Echange entities.
 */
@Service
public class EchangeService {

    @Autowired
    private EchangeRepository echangeRepository;

    /**
     * Creates a new Echange.
     *
     * @param echange The Echange entity to be created.
     * @return The created Echange entity.
     */
    public Echange creerEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    /**
     * Retrieves all Echange entities.
     *
     * @return A list of all Echange entities.
     */
    public List<Echange> obtenirTousLesEchanges() {
        return echangeRepository.findAll();
    }

    /**
     * Retrieves an Echange entity by its ID.
     *
     * @param id The ID of the Echange entity.
     * @return The Echange entity with the specified ID, or null if not found.
     */
    public Echange obtenirEchangeParId(Long id) {
        return echangeRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing Echange entity.
     *
     * @param echange The Echange entity to be updated.
     * @return The updated Echange entity.
     */
    public Echange mettreAJourEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    /**
     * Checks if an Echange entity exists by its ID.
     *
     * @param id The ID of the Echange entity.
     * @return true if the Echange entity exists, false otherwise.
     */
    public boolean echangeExist(Long id) {
        return echangeRepository.existsById(id);
    }
}