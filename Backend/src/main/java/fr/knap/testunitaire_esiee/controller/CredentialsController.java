package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.creerUtilisateur(utilisateur);
    }
}
