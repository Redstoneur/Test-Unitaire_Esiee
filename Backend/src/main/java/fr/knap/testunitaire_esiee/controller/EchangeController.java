package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.services.EchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/echanges")
public class EchangeController {

    @Autowired
    private EchangeService echangeService;

    @PostMapping("/create")
    public Echange creerEchange(@RequestBody Echange echange) {
        return echangeService.creerEchange(echange);
    }

    @GetMapping("/all")
    public List<Echange> obtenirTousLesEchanges() {
        return echangeService.obtenirTousLesEchanges();
    }

    @GetMapping("/{id}")
    public Echange obtenirUnEchange(@PathVariable Long id) {
        return echangeService.obtenirEchangeParId(id);
    }

    @PutMapping("/update")
    public Echange mettreAJourEchange(@RequestBody Echange echange) {
        if(echange.getId() == null)
            throw new IllegalArgumentException("L'id de l'échange ne peut pas être null");
        if (echangeService.echangeExist(echange.getId()))
            return echangeService.mettreAJourEchange(echange);
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Echange is not valid");
    }
}