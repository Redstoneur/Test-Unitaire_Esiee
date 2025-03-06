package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.respository.UtilisateurRepository;
import fr.knap.testunitaire_esiee.respository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private TokenRepository tokenRepository;

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
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByMailAndMdp(credentials.getMail(), credentials.getMdp());
        if (utilisateur.isPresent()) {
            Token token = new Token(credentials.getMail(), credentials.getMdp());
            return tokenRepository.save(token);
        }
        return null;
    }

    public boolean verifyToken(String tokenString) {
        Optional<Token> token = tokenRepository.findByToken(tokenString);
        return token.isPresent() && token.get().getExpirationDate().after(new Date());
    }

    public void disconnect(Token token) {
        Optional<Token> tokenEntity = tokenRepository.findByToken(token.getToken());
        if (tokenEntity.isPresent()) {
            Token existingToken = tokenEntity.get();
            existingToken.disconnect();
            tokenRepository.save(existingToken);
        }
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}