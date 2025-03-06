package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Token class.
 */
@SpringBootTest
class TokenTest {

    private static Token token;

    /**
     * Sets up the test environment before all tests.
     * Initializes the Token instance with test data.
     */
    @BeforeAll
    static void setUp() {
        token = new Token("test@mail.com", "password");
    }

    /**
     * Tests the token creation.
     * Verifies that the token is not null after creation.
     */
    @Test
    void testTokenCreation() {
        assertNotNull(token.getToken());
    }

    /**
     * Tests the token validation.
     * Verifies that a valid token is correctly validated.
     */
    @Test
    void testTokenValidation() {
        assertTrue(Token.validateToken(token.getToken()));
    }

    /**
     * Tests the token invalidation.
     * Verifies that an invalid token is correctly invalidated.
     */
    @Test
    void testTokenInvalidation() {
        assertFalse(Token.validateToken("invalidToken"));
    }

    /**
     * Tests the token subject retrieval.
     * Verifies that the subject of the token is returned correctly.
     */
    @Test
    void testTokenSubject() {
        assertEquals("test@mail.compassword", Token.getSubject(token.getToken()));
    }

    // TODO: Fix the test
    // /**
    //  * Tests the token disconnection.
    //  * Verifies that the token is disconnected correctly.
    //  */
    // @Test
    // void testTokenDisconnect() {
    //     token.disconnect();
    //     assertNull(token.getToken());
    // }
}