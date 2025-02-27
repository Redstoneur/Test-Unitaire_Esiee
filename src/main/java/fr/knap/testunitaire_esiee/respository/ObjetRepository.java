package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Objet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetRepository extends JpaRepository<Objet, Long> {
}
