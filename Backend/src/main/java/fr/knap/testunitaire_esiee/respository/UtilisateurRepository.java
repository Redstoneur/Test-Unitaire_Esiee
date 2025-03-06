package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Utilisateur entities.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Finds a user by email and password.
     *
     * @param mail The email of the user.
     * @param mdp  The password of the user.
     * @return An Optional containing the found Utilisateur, or empty if not found.
     */
    Optional<Utilisateur> findByMailAndMdp(String mail, String mdp);
}