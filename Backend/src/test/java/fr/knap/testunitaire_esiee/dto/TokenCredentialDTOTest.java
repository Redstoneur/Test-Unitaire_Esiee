package fr.knap.testunitaire_esiee.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TokenCredentialDTO class.
 */
@SpringBootTest
class TokenCredentialDTOTest {

    /**
     * Tests the constructor with parameters to ensure it sets the token field correctly.
     */
    @Test
    void constructorWithParameters_SetsToken() {
        String token = "sampleToken";
        TokenCredentialDTO tokenCredentialDTO = new TokenCredentialDTO(token);

        assertEquals(token, tokenCredentialDTO.getToken());
    }

    /**
     * Tests the setToken method to ensure it updates the token field.
     */
    @Test
    void setToken_UpdatesToken() {
        TokenCredentialDTO tokenCredentialDTO = new TokenCredentialDTO("initialToken");
        String newToken = "updatedToken";
        tokenCredentialDTO.setToken(newToken);

        assertEquals(newToken, tokenCredentialDTO.getToken());
    }

    /**
     * Tests the getToken method to ensure it returns the token field.
     */
    @Test
    void getToken_ReturnsToken() {
        String token = "sampleToken";
        TokenCredentialDTO tokenCredentialDTO = new TokenCredentialDTO(token);

        assertEquals(token, tokenCredentialDTO.getToken());
    }
}