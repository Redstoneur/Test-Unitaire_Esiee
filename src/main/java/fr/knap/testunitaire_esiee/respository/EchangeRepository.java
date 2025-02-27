package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Echange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EchangeRepository extends JpaRepository<Echange, Long> {
}
