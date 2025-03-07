package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

/**
 * Unit tests for the Token class.
 */
@SpringBootTest
class TokenTest {

    /**
     * Tests the constructor with parameters to ensure it sets the token and expiration date fields correctly.
     */
    @Test
    void constructorWithParametersSetsTokenAndExpirationDate() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertNotNull(token.getToken());
        assertNotNull(token.getExpirationDate());
        assertTrue(token.getExpirationDate().after(new Date()));
    }

    /**
     * Tests the default constructor to ensure it sets the token and expiration date fields to null.
     */
    @Test
    void defaultConstructorSetsFieldsToNull() {
        Token token = new Token();

        assertNull(token.getToken());
        assertNull(token.getExpirationDate());
    }

    /**
     * Tests the validateToken method with a valid token to ensure it returns true.
     */
    @Test
    void validateTokenValidTokenReturnsTrue() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertTrue(Token.validateToken(token.getToken()));
    }

    /**
     * Tests the validateToken method with an invalid token to ensure it returns false.
     */
    @Test
    void validateTokenInvalidTokenReturnsFalse() {
        assertFalse(Token.validateToken("invalidToken"));
    }

    /**
     * Tests the getSubject method with a valid token to ensure it returns the correct subject.
     */
    @Test
    void getSubjectValidTokenReturnsSubject() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertEquals(mail + "#" + mdp, Token.getSubject(token.getToken()));
    }

    /**
     * Tests the getEmail method with a valid token to ensure it returns the correct email.
     */
    @Test
    void getEmailValidTokenReturnsEmail() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertEquals(mail, Token.getEmail(token.getToken()));
    }

    /**
     * Tests the disconnect method to ensure it sets the expiration date to the current time.
     */
    @Test
    void disconnectSetsExpirationDateToCurrentTime() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);
        token.disconnect();

        assertTrue(token.getExpirationDate().before(new Date()) || token.getExpirationDate().equals(new Date()));
    }
}