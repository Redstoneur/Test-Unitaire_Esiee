package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.respository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetService {
    @Autowired
    private ObjetRepository objetRepository;

    public Objet creerObjet(Objet objet) {
        return objetRepository.save(objet);
    }

    public List<Objet> obtenirTousLesObjets() {
        return objetRepository.findAll();
    }

    public List<Objet> obtenirObjetsParUtilisateur(Long idUtilisateur) {
        return objetRepository.findByUtilisateurId(idUtilisateur);
    }
}
