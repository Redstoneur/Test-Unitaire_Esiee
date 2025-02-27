package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
