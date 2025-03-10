package fr.knap.testunitaire_esiee.config;

import com.github.javafaker.Faker;
import fr.knap.testunitaire_esiee.model.*;
import fr.knap.testunitaire_esiee.repository.EchangeRepository;
import fr.knap.testunitaire_esiee.repository.ObjetRepository;
import fr.knap.testunitaire_esiee.repository.UtilisateurRepository;
import fr.knap.testunitaire_esiee.services.ObjetService;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Configuration class for setting up initial data.
 */
@Configuration
public class Config {

    /**
     * Bean that runs on application startup to create initial data.
     *
     * @param utilisateurRepository The repository used for managing users.
     * @param objetRepository       The repository used for managing objects.
     * @param echangeRepository     The repository used for managing exchanges.
     * @param utilisateurService    The service used for managing users.
     * @param objetService          The service used for managing objects.
     * @return A CommandLineRunner that creates initial data.
     */
    @Bean
    CommandLineRunner commandLineRunner(
            UtilisateurRepository utilisateurRepository,
            ObjetRepository objetRepository,
            EchangeRepository echangeRepository,
            UtilisateurService utilisateurService,
            ObjetService objetService) {

        return args -> {
            int numberOfUsers = 5;
            int numberOfObjects = 40;
            int numberOfExchanges = 10;

            Faker faker = new Faker();

            List<Utilisateur> utilisateurs = new ArrayList<>();
            utilisateurs.add(new Utilisateur(
                    "admin",
                    "admin",
                    "admin@mail.fr",
                    "admin",
                    "admin"
            ));
            for (int i = 0; i < numberOfUsers; i++) {
                utilisateurs.add(new Utilisateur(
                        faker.name().firstName(),
                        faker.internet().password(),
                        faker.internet().emailAddress(),
                        faker.name().lastName(),
                        faker.name().firstName()
                ));
            }
            utilisateurRepository.saveAll(utilisateurs);
            numberOfUsers = utilisateurs.size();

            List<Objet> objets = new ArrayList<>();
            for (int i = 0; i < numberOfObjects; i++) {
                objets.add(new Objet(
                        utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)),
                        faker.commerce().productName(),
                        faker.lorem().sentence(),
                        CategorieObjet.values()[(int) (Math.random() * CategorieObjet.values().length)],
                        LocalDateTime.now().minusDays((int) (Math.random() * 60))
                ));
            }
            objetRepository.saveAll(objets);

            List<Echange> echanges = new ArrayList<>();
            for (int i = 0; i < numberOfExchanges; i++) {
                AtomicBoolean isNotUnique = new AtomicBoolean(true);
                AtomicLong id1 = new AtomicLong();
                AtomicLong id2 = new AtomicLong();
                do {
                    isNotUnique.set(false);
                    id1.set((long) ((Math.random() * numberOfObjects) + 1));
                    id2.set((long) ((Math.random() * numberOfObjects) + 1));
                    echanges.forEach(echange -> {
                                if (echange.getObjetPropose().getId().equals(id1.get()) ||
                                        echange.getObjetDemande().getId().equals(id1.get()) ||
                                        echange.getObjetPropose().getId().equals(id2.get()) ||
                                        echange.getObjetDemande().getId().equals(id2.get()))
                                    isNotUnique.set(true);
                            }
                    );
                } while (isNotUnique.get());
                Etat etat = Etat.values()[(int) (Math.random() * Etat.values().length)];
                LocalDateTime dateProposition = LocalDateTime.now().minusDays((int) (Math.random() * 30));
                LocalDateTime dateCloture = etat == Etat.ATTENTE ? null : LocalDateTime.now().minusDays((int) (Math.random() * 10));
                echanges.add(new Echange(
                        objetService.obtenirObjetParId(id1.get()),
                        objetService.obtenirObjetParId(id2.get()),
                        dateProposition,
                        etat,
                        dateCloture
                ));
            }
            echangeRepository.saveAll(echanges);
        };
    }
}