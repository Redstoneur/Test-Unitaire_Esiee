package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
