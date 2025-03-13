package fr.knap.testunitaire_esiee.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing an exchange between objects.
 */
@Getter
@Setter
public class EchangeBufferDTO {

    /**
     * Identifier of the object proposed for exchange.
     */
    private Long idObjetPropose;

    /**
     * Identifier of the object requested in the exchange.
     */
    private Long idObjetRecherche;

    /**
     * Constructs a new {@code EchangeBufferDTO} with the given object identifiers.
     *
     * @param idObjetPropose   the identifier of the object proposed for exchange
     * @param idObjetRecherche the identifier of the object requested for exchange
     */
    public EchangeBufferDTO(Long idObjetPropose, Long idObjetRecherche) {
        this.idObjetPropose = idObjetPropose;
        this.idObjetRecherche = idObjetRecherche;
    }
}