package fr.knap.testunitaire_esiee.config;

import fr.knap.testunitaire_esiee.model.*;
import fr.knap.testunitaire_esiee.respository.EchangeRepository;
import fr.knap.testunitaire_esiee.respository.ObjetRepository;
import fr.knap.testunitaire_esiee.respository.UtilisateurRepository;

import fr.knap.testunitaire_esiee.services.ObjetService;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * Configuration class for setting up initial data.
 */
@Configuration
public class Config {

    /**
     * Bean that runs on application startup to create initial greetings.
     *
     * @param utilisateurRepository The service used for managing greetings.
     * @return A CommandLineRunner that creates initial greetings.
     */
    @Bean
    CommandLineRunner commandLineRunner(UtilisateurRepository utilisateurRepository, ObjetRepository objetRepository, EchangeRepository echangeRepository, UtilisateurService utilisateurService, ObjetService objetService) {

        return args -> {
            for (int i = 0; i < 10; i++) {
                Utilisateur user = new Utilisateur("hubbhubhbuhb" + i, "aaaa", "aaaaaa", "qiudifsiudfqs", "eiqrnoifbqiub");
                utilisateurRepository.save(user);
            }
            for (int i = 0; i < 5; i++) {
                Objet objet = new Objet(utilisateurService.obtenirUtilisateurParId(1L), "aaaa", "aaaaaa", CategorieObjet.GAMING, LocalDateTime.now());
                objetRepository.save(objet);
            }
            for (int i = 0; i < 3; i++) {
                Objet objet = new Objet(utilisateurService.obtenirUtilisateurParId(1L), "aaaa", "aaaaaa", CategorieObjet.LITTERATURE, LocalDateTime.now());
                objetRepository.save(objet);
            }
                Objet objet = new Objet(utilisateurService.obtenirUtilisateurParId(5L), "aaaa", "aaaaaa", CategorieObjet.LITTERATURE, LocalDateTime.now());
                objetRepository.save(objet);
                Objet objet2 = new Objet(utilisateurService.obtenirUtilisateurParId(4L), "aaaa", "aaaaaa", CategorieObjet.GAMING, LocalDateTime.now());
                objetRepository.save(objet2);
            Echange echange = new Echange(objetService.obtenirObjetParId(9L), objetService.obtenirObjetParId(3L), LocalDateTime.now(), Etat.ATTENTE, null);
            echangeRepository.save(echange);
            Echange echange2 = new Echange(objetService.obtenirObjetParId(8L), objetService.obtenirObjetParId(5L), LocalDateTime.now(), Etat.ACCEPTE, LocalDateTime.now().plusDays(2));
            echangeRepository.save(echange2);
            Echange echange3 = new Echange(objetService.obtenirObjetParId(10L), objetService.obtenirObjetParId(4L), LocalDateTime.now().minusDays(1), Etat.REFUSE, LocalDateTime.now().plusDays(3));
            echangeRepository.save(echange3);
        };
    }
}