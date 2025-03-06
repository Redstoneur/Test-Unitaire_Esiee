package fr.knap.testunitaire_esiee.services;

    import fr.knap.testunitaire_esiee.model.Echange;
    import fr.knap.testunitaire_esiee.respository.EchangeRepository;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    /**
     * Unit tests for the EchangeService class.
     */
    class EchangeServiceTest {

        @Mock
        private EchangeRepository echangeRepository;

        @InjectMocks
        private EchangeService echangeService;

        /**
         * Sets up the test environment before each test.
         * Initializes the mocks and injects them into the EchangeService instance.
         */
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        /**
         * Tests the creerEchange method of the EchangeService class.
         * Verifies that an exchange is created and saved correctly.
         */
        @Test
        void testCreerEchange() {
            Echange echange = new Echange();
            when(echangeRepository.save(echange)).thenReturn(echange);
            assertEquals(echange, echangeService.creerEchange(echange));
        }

        /**
         * Tests the obtenirTousLesEchanges method of the EchangeService class.
         * Verifies that all exchanges are retrieved correctly.
         */
        @Test
        void testObtenirTousLesEchanges() {
            echangeService.obtenirTousLesEchanges();
            verify(echangeRepository, times(1)).findAll();
        }
    }