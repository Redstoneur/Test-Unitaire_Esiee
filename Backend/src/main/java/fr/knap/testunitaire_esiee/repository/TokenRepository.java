package fr.knap.testunitaire_esiee.repository;

import fr.knap.testunitaire_esiee.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Token entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface TokenRepository extends JpaRepository<Token, Long> {

    /**
     * Finds a token by its string value.
     *
     * @param token The token string.
     * @return An Optional containing the found Token, or empty if not found.
     */
    Optional<Token> findByToken(String token);
}