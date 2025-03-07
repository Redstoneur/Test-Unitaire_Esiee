package fr.knap.testunitaire_esiee.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing a token credential.
 */
@Getter
@Setter
public class TokenCredentialDTO {

    /**
     * The token string.
     */
    private String token;

    /**
     * Constructs a new TokenCredentialDTO object with the specified token.
     *
     * @param token The token string.
     */
    public TokenCredentialDTO(String token) {
        this.token = token;
    }

}