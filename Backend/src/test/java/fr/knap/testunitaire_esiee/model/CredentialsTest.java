package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CredentialsTest {

    @Test
    void constructorWithParameters_SetsMailAndMdp() {
        Credentials credentials = new Credentials("user@example.com", "password123");
        assertEquals("user@example.com", credentials.getMail());
        assertEquals("password123", credentials.getMdp());
    }

    @Test
    void defaultConstructor_SetsMailAndMdpToNull() {
        Credentials credentials = new Credentials();
        assertNull(credentials.getMail());
        assertNull(credentials.getMdp());
    }

    @Test
    void setMail_UpdatesMail() {
        Credentials credentials = new Credentials();
        credentials.setMail("newuser@example.com");
        assertEquals("newuser@example.com", credentials.getMail());
    }

    @Test
    void setMdp_UpdatesMdp() {
        Credentials credentials = new Credentials();
        credentials.setMdp("newpassword123");
        assertEquals("newpassword123", credentials.getMdp());
    }
}