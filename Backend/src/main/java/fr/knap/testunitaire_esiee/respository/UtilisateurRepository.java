package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    boolean existsByMail(String mail);

    boolean existsByMdp(String mdp);
}
