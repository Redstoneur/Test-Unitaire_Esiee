package fr.knap.testunitaire_esiee.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing a token credential.
 */
@Getter
@Setter
public class TokenCredential {

    /**
     * The token string.
     */
    private String token;

    /**
     * Constructs a new TokenCredential object with the specified token.
     *
     * @param token The token string.
     */
    public TokenCredential(String token) {
        this.token = token;
    }

}