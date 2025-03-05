package fr.knap.testunitaire_esiee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TestUnitaireEsieeApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void applicationStartsSuccessfully() {
        assertDoesNotThrow(() -> TestUnitaireEsieeApplication.main(new String[] {}));
    }

    @Test
    void applicationContextLoads() {
        assertNotNull(applicationContext, "The application context should have loaded.");
    }
}