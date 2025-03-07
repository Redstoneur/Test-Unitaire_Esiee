package fr.knap.testunitaire_esiee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the TestUnitaireEsieeApplication class.
 */
@SpringBootTest
class TestUnitaireEsieeApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Tests that the application starts successfully.
     * Verifies that no exceptions are thrown when the main method is called.
     */
    @Test
    void applicationStartsSuccessfully() {
        assertDoesNotThrow(() -> TestUnitaireEsieeApplication.main(new String[] {}));
    }

    /**
     * Tests that the application context loads successfully.
     * Verifies that the application context is not null.
     */
    @Test
    void applicationContextLoads() {
        assertNotNull(applicationContext, "The application context should have loaded.");
    }
}