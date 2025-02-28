package fr.knap.testunitaire_esiee.config;

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
     * @param greetingServicesImpl The service used for managing greetings.
     * @return A CommandLineRunner that creates initial greetings.
     */
   /* @Bean
    CommandLineRunner commandLineRunner(GreetingServicesImpl greetingServicesImpl) {
        return args -> {
            greetingServicesImpl.createGreeting(new Greeting("pavel"));
            greetingServicesImpl.createGreeting(new Greeting("Alipio"));
            greetingServicesImpl.createGreeting(new Greeting("Killyan"));
            greetingServicesImpl.createGreeting(new Greeting("Nicolas"));
            greetingServicesImpl.createGreeting(new Greeting("Anaïs"));
            greetingServicesImpl.createGreeting(new Greeting("Edouard"));
            greetingServicesImpl.createGreeting(new Greeting("George"));
        };
    }*/
}