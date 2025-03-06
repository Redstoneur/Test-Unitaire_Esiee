package fr.knap.testunitaire_esiee.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenCredential {

    private String token;

    public TokenCredential(String token) {
        this.token = token;
    }

}
