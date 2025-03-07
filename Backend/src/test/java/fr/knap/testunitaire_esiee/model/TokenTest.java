package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

@SpringBootTest
class TokenTest {

    @Test
    void constructorWithParameters_SetsTokenAndExpirationDate() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertNotNull(token.getToken());
        assertNotNull(token.getExpirationDate());
        assertTrue(token.getExpirationDate().after(new Date()));
    }

    @Test
    void defaultConstructor_SetsFieldsToNull() {
        Token token = new Token();

        assertNull(token.getToken());
        assertNull(token.getExpirationDate());
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertTrue(Token.validateToken(token.getToken()));
    }

    @Test
    void validateToken_InvalidToken_ReturnsFalse() {
        assertFalse(Token.validateToken("invalidToken"));
    }

    @Test
    void getSubject_ValidToken_ReturnsSubject() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertEquals(mail + "#" + mdp, Token.getSubject(token.getToken()));
    }

    @Test
    void getEmail_ValidToken_ReturnsEmail() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);

        assertEquals(mail, Token.getEmail(token.getToken()));
    }

    @Test
    void disconnect_SetsExpirationDateToCurrentTime() {
        String mail = "user@example.com";
        String mdp = "password123";
        Token token = new Token(mail, mdp);
        token.disconnect();

        assertTrue(token.getExpirationDate().before(new Date()) || token.getExpirationDate().equals(new Date()));
    }
}