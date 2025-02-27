package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.services.ObjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objets")
public class ObjetController {

    @Autowired
    private ObjetService objetService;

    @PostMapping
    public Objet creerObjet(@RequestBody Objet objet) {
        return objetService.creerObjet(objet);
    }

    @GetMapping
    public List<Objet> obtenirTousLesObjets() {
        return objetService.obtenirTousLesObjets();
    }

    @GetMapping("/{id}")
    public Objet obtenirObjetParId(@PathVariable Long id) {
        return objetService.obtenirObjetParId(id);
    }

    @PutMapping("/{id}")
    public Objet mettreAJourObjet(@PathVariable Long id, @RequestBody Objet objet) {
        return objetService.mettreAJourObjet(id, objet);
    }

    @DeleteMapping("/{id}")
    public void supprimerObjet(@PathVariable Long id) {
        objetService.supprimerObjet(id);
    }
}