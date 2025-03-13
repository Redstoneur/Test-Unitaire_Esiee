package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.EchangeBufferDTO;
import fr.knap.testunitaire_esiee.dto.EchangeEtatDTO;
import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.services.EchangeService;
import fr.knap.testunitaire_esiee.services.ObjetService;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * REST controller for managing exchanges.
 */
@RestController
@RequestMapping("/api/echanges")
public class EchangeController {

    @Autowired
    private EchangeService echangeService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private ObjetService objetService;

    /**
     * Creates a new exchange.
     *
     * @param echangeBufferDTO The exchange to be created.
     * @return The created exchange.
     */
    @PostMapping("/create")
    public Echange creerEchange(@RequestHeader("Authorization") String authToken, @RequestBody EchangeBufferDTO echangeBufferDTO) {
        if (utilisateurService.verifyToken(authToken)) {
            Echange echange = new Echange(
                    objetService.obtenirObjetParId(echangeBufferDTO.getIdObjetPropose()),
                    objetService.obtenirObjetParId(echangeBufferDTO.getIdObjetRecherche())
            );

            return echangeService.creerEchange(echange);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    /**
     * Retrieves all exchanges.
     *
     * @return A list of all exchanges.
     */
    @GetMapping("/all")
    public List<Echange> obtenirTousLesEchanges() {
        return echangeService.obtenirTousLesEchanges();
    }

    /**
     * Retrieves a specific exchange by its ID.
     *
     * @param id The ID of the exchange to retrieve.
     * @return The exchange with the specified ID.
     */
    @GetMapping("/{id}")
    public Echange obtenirUnEchange(@RequestHeader("Authorization") String authToken, @PathVariable Long id) {
        if (utilisateurService.verifyToken(authToken))
            return echangeService.obtenirEchangeParId(id);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    /**
     * Updates an existing exchange.
     *
     * @param echangeEtatDTO The exchange to be updated.
     * @return The updated exchange.
     * @throws IllegalArgumentException if the exchange ID is null.
     * @throws ResponseStatusException  if the exchange does not exist.
     */
    @PutMapping("/update")
    public Echange mettreAJourEchange(@RequestHeader("Authorization") String authToken,
                                      @RequestBody EchangeEtatDTO echangeEtatDTO) {
        if (utilisateurService.verifyToken(authToken)) {
            if (echangeEtatDTO.getId() <= 0)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Echange ID is invalid");

            Echange echange = echangeService.obtenirEchangeParId(echangeEtatDTO.getId());

            if (echange == null)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Echange does not exist");

            echange.setEtatEchange(echangeEtatDTO.getEtat());

            if (echangeService.echangeExist(echange.getId()))
                return echangeService.mettreAJourEchange(echange);

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Echange is not valid");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is not valid");
    }
}