package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.dto.UtilisateurDTO;
import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.repository.UtilisateurRepository;
import fr.knap.testunitaire_esiee.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

/**
 * Service class for managing Utilisateur entities.
 */
@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Creates a new Utilisateur.
     *
     * @param utilisateur The Utilisateur entity to be created.
     * @return The created Utilisateur entity.
     */
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Retrieves all Utilisateur entities.
     *
     * @return A list of all Utilisateur entities.
     */
    public List<Utilisateur> obtenirTousLesUtilisateurs() {


        return utilisateurRepository.findAll();
    }

    /**
     * Retrieves a Utilisateur entity by its ID.
     *
     * @param id The ID of the Utilisateur entity.
     * @return The Utilisateur entity with the specified ID, or null if not found.
     */
    public Utilisateur obtenirUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public UtilisateurDTO obtenirUtilisateurInfoParId(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            Utilisateur u = utilisateur.get();
            return new UtilisateurDTO(u.getPseudo(), u.getNom(), u.getPrenom());
        }
        return null;
    }

    public UtilisateurDTO obtenirUtilisateurPseudoParId(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            Utilisateur u = utilisateur.get();
            return new UtilisateurDTO(u.getPseudo());
        }
        return null;
    }

    /**
     * Updates an existing Utilisateur entity.
     *
     * @param id          The ID of the Utilisateur entity to be updated.
     * @param utilisateur The Utilisateur entity with updated information.
     * @return The updated Utilisateur entity, or null if the entity does not exist.
     */
    public Utilisateur mettreAJourUtilisateur(Long id, Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(id)) {
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    /**
     * Authenticates a user and generates a token.
     *
     * @param credentials The user's credentials.
     * @return The generated Token if authentication is successful, or null if not.
     */
    public Token login(Credentials credentials) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByMailAndMdp(
                credentials.getMail(), credentials.getMdp()
        );
        if (utilisateur.isPresent()) {
            Token token = new Token(credentials.getMail(), credentials.getMdp());
            return tokenRepository.save(token);
        }
        return null;
    }

    /**
     * Verifies if a token is valid.
     *
     * @param tokenString The token string to be verified.
     * @return true if the token is valid, false otherwise.
     */
    public boolean verifyToken(String tokenString) {
        Optional<Token> token = tokenRepository.findByToken(tokenString);
        return token.isPresent() && token.get().getExpirationDate().after(new Date());
    }

    /**
     * Disconnects a user by invalidating their token.
     *
     * @param token The token string to be invalidated.
     */
    public void disconnect(String token) {
        Optional<Token> tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity.isPresent()) {
            Token existingToken = tokenEntity.get();
            existingToken.disconnect();
            tokenRepository.save(existingToken);
        }
    }

    /**
     * Deletes a Utilisateur entity by its ID.
     *
     * @param id The ID of the Utilisateur entity to be deleted.
     */
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    /**
     * Retrieves a Utilisateur entity by its email.
     *
     * @param mail The email of the Utilisateur entity.
     * @return The Utilisateur entity with the specified email, or null if not found.
     */
    public Utilisateur obtenirUtilisateurParMail(String mail) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByMail(mail);
        return utilisateur.orElse(null);
    }

    /**
     * Retrieves a Utilisateur entity by its token.
     *
     * @param token The token of the Utilisateur entity.
     * @return The Utilisateur entity with the specified token, or null if not found.
     */
    public Utilisateur obtenirUtilisateurParToken(String token) {
        Optional<Token> tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity.isPresent()) {
            return obtenirUtilisateurParMail(Token.getEmail(token));
        }
        return null;
    }

    /**
     * Retrieves a Utilisateur entity by its token.
     *
     * @param token The token of the Utilisateur entity.
     * @return The Utilisateur entity with the specified token, or null if not found.
     */
    public UtilisateurDTO obtenirUtilisateurInfoParToken(String token) {
        Utilisateur u = obtenirUtilisateurParToken(token);
        return new UtilisateurDTO(u.getPseudo(), u.getNom(), u.getPrenom());
    }
}