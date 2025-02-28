package fr.knap.testunitaire_esiee.config;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.respository.UtilisateurRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    CommandLineRunner commandLineRunner(UtilisateurRepository utilisateurRepository) {

        return args -> {
            for (int i = 0; i < 10; i++) {            Utilisateur user = new Utilisateur("hubbhubhbuhb"+i,"aaaa","aaaaaa","qiudifsiudfqs","eiqrnoifbqiub");
            utilisateurRepository.save(user);
            }
            
        };
    }
}