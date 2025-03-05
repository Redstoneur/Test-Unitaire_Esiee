package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenTest {

    private static Token token;

    @BeforeAll
    static void setUp() {
        token = new Token("test@mail.com", "password");
    }

    @Test
    void testTokenCreation() {
        assertNotNull(token.getToken());
    }

    @Test
    void testTokenValidation() {
        assertTrue(Token.validateToken(token.getToken()));
    }

    @Test
    void testTokenInvalidation() {
        assertFalse(Token.validateToken("invalidToken"));
    }

// todo: fix this test
//    @Test
//    void testTokenSubject() {
//        assertEquals("test@mail.compassword", Token.getSubject(token.getToken()));
//    }

    @Test
    void testTokenDisconnect() {
        token.disconnect();
        assertNull(token.getToken());
    }
}