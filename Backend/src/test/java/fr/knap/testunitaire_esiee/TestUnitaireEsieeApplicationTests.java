package fr.knap.testunitaire_esiee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestUnitaireEsieeApplicationTests {

    @Test
    void applicationStartsSuccessfully() {
        TestUnitaireEsieeApplication.main(new String[] {});
    }

    @Test
    void applicationContextLoads() {
        // This test will automatically fail if the application context cannot start
    }
}