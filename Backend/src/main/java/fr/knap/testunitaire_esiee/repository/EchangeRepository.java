package fr.knap.testunitaire_esiee.repository;

import fr.knap.testunitaire_esiee.model.Echange;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Echange entities.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface EchangeRepository extends JpaRepository<Echange, Long> {
}