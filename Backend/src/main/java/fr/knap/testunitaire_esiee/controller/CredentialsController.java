package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.TokenCredential;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/credentials")
public class CredentialsController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String test() {
        return "Hello World";
    }

    @PostMapping("/register")
    public TokenCredential creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurService.creerUtilisateur(utilisateur);
        TokenCredential tokenCredential = new TokenCredential(utilisateurService.login(new Credentials(utilisateur.getMail(), utilisateur.getMdp())).getToken());
        return tokenCredential;
    }

    @PostMapping("/login")
    public TokenCredential getConnexionToken(@RequestBody Credentials credentials) {
        Token token = utilisateurService.login(credentials);
        if (token != null) {
            return new TokenCredential(token.getToken());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }
    }

    @PostMapping("/disconnect")
    public void disconnect(@RequestBody TokenCredential token) {
        if (!utilisateurService.verifyToken(token.getToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid");
        }
        utilisateurService.disconnect(token.getToken());
        throw new ResponseStatusException(HttpStatus.OK, "Disconnection successful");
    }

    @PostMapping("/verifyToken")
    public void verifyToken(@RequestBody TokenCredential token) {
        if (utilisateurService.verifyToken(token.getToken())) {
            throw new ResponseStatusException(HttpStatus.OK, "Token is valid");
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is invalid");
        }
    }
}