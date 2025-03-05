package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CredentialsTest {

    private static Credentials credentials;
    private static Credentials credentials2;

    @BeforeAll
    static void setUp() {
        credentials = new Credentials("test@mail.com", "password");
        credentials2 = new Credentials();
        credentials2.setMail("test2@mail.com");
        credentials2.setMdp("password2");
    }

    @Test
    void testCredentialsMail() {
        assertEquals("test@mail.com", credentials.getMail());
        assertEquals("test2@mail.com", credentials2.getMail());
    }

    @Test
    void testCredentialsMdp() {
        assertEquals("password", credentials.getMdp());
        assertEquals("password2", credentials2.getMdp());
    }
}