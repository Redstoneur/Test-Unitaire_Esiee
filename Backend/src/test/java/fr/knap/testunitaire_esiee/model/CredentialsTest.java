package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Credentials class.
 */
@SpringBootTest
class CredentialsTest {

    /**
     * Tests the constructor with parameters to ensure it sets the mail and mdp fields correctly.
     */
    @Test
    void constructorWithParametersSetsMailAndMdp() {
        Credentials credentials = new Credentials("user@example.com", "password123");
        assertEquals("user@example.com", credentials.getMail());
        assertEquals("password123", credentials.getMdp());
    }

    /**
     * Tests the default constructor to ensure it sets the mail and mdp fields to null.
     */
    @Test
    void defaultConstructorSetsMailAndMdpToNull() {
        Credentials credentials = new Credentials();
        assertNull(credentials.getMail());
        assertNull(credentials.getMdp());
    }

    /**
     * Tests the setMail method to ensure it updates the mail field.
     */
    @Test
    void setMailUpdatesMail() {
        Credentials credentials = new Credentials();
        credentials.setMail("newuser@example.com");
        assertEquals("newuser@example.com", credentials.getMail());
    }

    /**
     * Tests the setMdp method to ensure it updates the mdp field.
     */
    @Test
    void setMdpUpdatesMdp() {
        Credentials credentials = new Credentials();
        credentials.setMdp("newpassword123");
        assertEquals("newpassword123", credentials.getMdp());
    }
}