package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.services.EchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/echanges")
public class EchangeController {

    @Autowired
    private EchangeService echangeService;

    @PostMapping
    public Echange creerEchange(@RequestBody Echange echange) {
        return echangeService.creerEchange(echange);
    }

    @GetMapping
    public List<Echange> obtenirTousLesEchanges() {
        return echangeService.obtenirTousLesEchanges();
    }
}