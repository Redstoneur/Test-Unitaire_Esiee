package fr.knap.testunitaire_esiee.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenCredentialDTOTest {

    @Test
    void constructorWithParameters_SetsToken() {
        String token = "sampleToken";
        TokenCredentialDTO tokenCredentialDTO = new TokenCredentialDTO(token);

        assertEquals(token, tokenCredentialDTO.getToken());
    }

    @Test
    void setToken_UpdatesToken() {
        TokenCredentialDTO tokenCredentialDTO = new TokenCredentialDTO("initialToken");
        String newToken = "updatedToken";
        tokenCredentialDTO.setToken(newToken);

        assertEquals(newToken, tokenCredentialDTO.getToken());
    }

    @Test
    void getToken_ReturnsToken() {
        String token = "sampleToken";
        TokenCredentialDTO tokenCredentialDTO = new TokenCredentialDTO(token);

        assertEquals(token, tokenCredentialDTO.getToken());
    }
}