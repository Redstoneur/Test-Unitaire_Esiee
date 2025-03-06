package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.respository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur obtenirUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur mettreAJourUtilisateur(Long id, Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(id)) {
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    public Token login(Credentials credentials) {
        if(utilisateurRepository.existsByMail(credentials.getMail()) && utilisateurRepository.existsByMdp(credentials.getMdp()))
        {
            return new Token(credentials.getMail(), credentials.getMdp());
        } else {
            return null;
        }
    }

    public Token disconnect(Token token) {
        token.disconnect();
        return token;
    }

    public boolean verifyToken(String token) {
 return Token.validateToken(token);
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
