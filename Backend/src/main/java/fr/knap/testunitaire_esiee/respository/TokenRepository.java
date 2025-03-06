package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}