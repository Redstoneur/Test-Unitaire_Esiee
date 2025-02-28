package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Objet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetRepository extends JpaRepository<Objet, Long> {

    List<Objet> findByUtilisateurId(Long idUtilisateur);
}
