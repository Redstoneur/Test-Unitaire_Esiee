package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Objet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Objet entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface ObjetRepository extends JpaRepository<Objet, Long> {

    /**
     * Finds a list of objects by the user ID.
     *
     * @param idUtilisateur The ID of the user.
     * @return A list of objects associated with the specified user ID.
     */
    List<Objet> findByUtilisateurId(Long idUtilisateur);
}