package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.dto.TokenCredentialDTO;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for managing user credentials.
 */
@RestController
@RequestMapping("/api/credentials")
public class CredentialsController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Registers a new user and returns a token credential.
     *
     * @param utilisateur The user to be registered.
     * @return The token credential for the newly registered user.
     */
    @PostMapping("/register")
    public TokenCredentialDTO creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurService.creerUtilisateur(utilisateur);
        return new TokenCredentialDTO(
                utilisateurService.login(
                        new Credentials(
                                utilisateur.getMail(),
                                utilisateur.getMdp())
                ).getToken()
        );
    }

    /**
     * Authenticates a user and returns a token credential.
     *
     * @param credentials The user's credentials.
     * @return The token credential if authentication is successful.
     * @throws ResponseStatusException if the token is not found.
     */
    @PostMapping("/login")
    public TokenCredentialDTO getConnexionToken(@RequestBody Credentials credentials) {
        Token token = utilisateurService.login(credentials);
        if (token != null) {
            return new TokenCredentialDTO(token.getToken());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }
    }

    /**
     * Disconnects a user by invalidating their token.
     *
     * @param token The token credential to be invalidated.
     * @throws ResponseStatusException if the token is not valid.
     */
    @PostMapping("/disconnect")
    public void disconnect(@RequestBody TokenCredentialDTO token) {
        if (!utilisateurService.verifyToken(token.getToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid");
        }
        utilisateurService.disconnect(token.getToken());
        throw new ResponseStatusException(HttpStatus.OK, "Disconnection successful");
    }

    /**
     * Verifies the validity of a token.
     *
     * @param token The token credential to be verified.
     * @throws ResponseStatusException if the token is invalid.
     */
    @PostMapping("/verifyToken")
    public void verifyToken(@RequestBody TokenCredentialDTO token) {
        if (utilisateurService.verifyToken(token.getToken())) {
            throw new ResponseStatusException(HttpStatus.OK, "Token is valid");
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is invalid");
        }
    }
}