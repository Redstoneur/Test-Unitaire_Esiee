package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Credentials class.
 */
@SpringBootTest
class CredentialsTest {

    private static Credentials credentials;
    private static Credentials credentials2;

    /**
     * Sets up the test environment before all tests.
     * Initializes the Credentials instances with test data.
     */
    @BeforeAll
    static void setUp() {
        credentials = new Credentials("test@mail.com", "password");
        credentials2 = new Credentials();
        credentials2.setMail("test2@mail.com");
        credentials2.setMdp("password2");
    }

    /**
     * Tests the getMail method of the Credentials class.
     * Verifies that the email addresses are returned correctly.
     */
    @Test
    void testCredentialsMail() {
        assertEquals("test@mail.com", credentials.getMail());
        assertEquals("test2@mail.com", credentials2.getMail());
    }

    /**
     * Tests the getMdp method of the Credentials class.
     * Verifies that the passwords are returned correctly.
     */
    @Test
    void testCredentialsMdp() {
        assertEquals("password", credentials.getMdp());
        assertEquals("password2", credentials2.getMdp());
    }
}