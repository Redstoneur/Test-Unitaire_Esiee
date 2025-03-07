package fr.knap.testunitaire_esiee.config;

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
    CommandLineRunner commandLineRunner(
            UtilisateurRepository utilisateurRepository,
            ObjetRepository objetRepository,
            EchangeRepository echangeRepository,
            UtilisateurService utilisateurService,
            ObjetService objetService) {

        return args -> {
            List<Utilisateur> utilisateurs = new ArrayList<>();

            utilisateurs.add(new Utilisateur(
                    "Nicolas",
                    "testnicolas", "nicolas@test.testa",
                    "Test",
                    "Nicolas"
            ));

            utilisateurs.add(new Utilisateur(
                    "Sophie",
                    "sophietest", "sophie@test.com",
                    "Test",
                    "Sophie"
            ));

            utilisateurs.add(new Utilisateur(
                    "Jean",
                    "jeanpierre", "jean.pierre@test.net",
                    "Pierre",
                    "Jean"
            ));

            utilisateurs.add(new Utilisateur(
                    "Laura",
                    "lauratest", "laura@test.org",
                    "Test",
                    "Laura"
            ));

            utilisateurs.add(new Utilisateur(
                    "David",
                    "david123", "david@test.fr",
                    "Test",
                    "David"
            ));

            utilisateurs.forEach(utilisateurRepository::save);

            int numberOfUsers = utilisateurs.size();

            Objet objet = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "NVIDIA RTX 4070SUPER",
                    "Carte graphique Nvidia RTX 4070SUPER",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet);

            Objet objet1 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Fauteuil IKEA Markus",
                    "Fauteuil de bureau ergonomique avec support lombaire.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet1);

            Objet objet2 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Table basse Maison du Monde",
                    "Table basse en bois massif avec rangement intégré.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet2);

            Objet objet3 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Tondeuse Bosch Rotak 43",
                    "Tondeuse à gazon électrique avec bac de ramassage.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet3);

            Objet objet4 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Arrosoir Gardena 10L",
                    "Arrosoir robuste en métal avec bec verseur précis.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet4);

            Objet objet5 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Clavier mécanique Logitech G915",
                    "Clavier mécanique sans fil avec switchs tactiles.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet5);

            Objet objet6 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Écran Dell UltraSharp 27\"",
                    "Écran 4K UHD avec technologie IPS.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet6);

            Objet objet7 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Manette Xbox Elite Series 2",
                    "Manette pro personnalisable avec paddles.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet7);

            Objet objet8 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Casque SteelSeries Arctis Pro",
                    "Casque gaming haute fidélité avec son surround.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet8);

            Objet objet9 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Perceuse Bosch GSR 12V",
                    "Perceuse-visseuse sans fil avec batterie lithium-ion.",
                    CategorieObjet.OUTILS, LocalDateTime.now());
            objetRepository.save(objet9);

            Objet objet10 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Timbre Napoléon III 1863",
                    "Timbre rare en bon état, valeur collection.",
                    CategorieObjet.COLLECTION, LocalDateTime.now());
            objetRepository.save(objet10);

            Objet objet11 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Roman 'Dune' de Frank Herbert",
                    "Édition collector avec couverture rigide.",
                    CategorieObjet.LITTERATURE, LocalDateTime.now());
            objetRepository.save(objet11);

            Objet objet12 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Sweat Nike Tech Fleece",
                    "Sweat à capuche gris, taille L.",
                    CategorieObjet.VETEMENTS, LocalDateTime.now());
            objetRepository.save(objet12);

            Objet objet13 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Aspirateur Dyson V11",
                    "Aspirateur sans fil puissant avec station d'accueil.",
                    CategorieObjet.ELECTROMENAGER, LocalDateTime.now());
            objetRepository.save(objet13);

            Objet objet14 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Porte-clés Marvel",
                    "Porte-clés en métal avec logo Avengers.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet14);

            Objet objet15 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Tournevis multifonction Bosch",
                    "Tournevis avec embouts interchangeables.",
                    CategorieObjet.OUTILS, LocalDateTime.now());
            objetRepository.save(objet15);

            Objet objet16 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Console PS5",
                    "Console Sony PlayStation 5 en excellent état.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet16);

            Objet objet17 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "MacBook Pro M2",
                    "Ordinateur portable Apple, 16 Go RAM, 512 Go SSD.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet17);

            Objet objet18 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Veste The North Face",
                    "Veste imperméable, idéale pour l'hiver.",
                    CategorieObjet.VETEMENTS, LocalDateTime.now());
            objetRepository.save(objet18);

            Objet objet19 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Machine à café Nespresso Vertuo",
                    "Machine avec capsules compatibles Vertuo.",
                    CategorieObjet.ELECTROMENAGER, LocalDateTime.now());
            objetRepository.save(objet19);

            Objet objet20 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Lampe Philips Hue",
                    "Lampe connectée avec variation de couleurs.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet20);

            Objet objet21 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Grille-pain SMEG",
                    "Grille-pain rétro avec thermostat réglable.",
                    CategorieObjet.ELECTROMENAGER, LocalDateTime.now());
            objetRepository.save(objet21);

            Objet objet22 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Tondeuse Husqvarna Automower",
                    "Robot tondeuse autonome.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet22);

            Objet objet23 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Préférence d'échange : Roomba",
                    "Aspirateur intelligent recherché en échange.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet23);

            Objet objet24 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Sac à dos Eastpak",
                    "Sac à dos noir, capacité 24L.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet24);

            Objet objet25 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Jeu Zelda: Tears of the Kingdom",
                    "Jeu Nintendo Switch, édition standard.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet25);

            Objet objet26 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Bureau en bois massif",
                    "Bureau spacieux avec tiroirs intégrés.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet26);

            Objet objet27 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Souris Logitech MX Master 3",
                    "Souris sans fil ergonomique pour travail intensif.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet27);

            Objet objet28 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Livres Harry Potter édition Gallimard",
                    "Collection complète, bon état.",
                    CategorieObjet.LITTERATURE, LocalDateTime.now());
            objetRepository.save(objet28);

            Objet objet29 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Lunettes de soleil Ray-Ban Aviator",
                    "Verres polarisés, monture dorée.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet29);

            Objet objet30 = new Objet(
                    utilisateurService.obtenirUtilisateurParId((long) ((Math.random() * numberOfUsers) + 1)), "Gants de jardinage Wolf-Garten",
                    "Gants résistants pour la taille et plantation.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet30);

            Echange echange = new Echange(
                    objetService.obtenirObjetParId(9L), objetService.obtenirObjetParId((long) ((Math.random() * numberOfUsers) + 1)),
                    LocalDateTime.now(), Etat.ATTENTE, null);
            echangeRepository.save(echange);
            Echange echange2 = new Echange(
                    objetService.obtenirObjetParId(8L), objetService.obtenirObjetParId((long) ((Math.random() * numberOfUsers) + 1)),
                    LocalDateTime.now(), Etat.ACCEPTE, LocalDateTime.now().plusDays(2));
            echangeRepository.save(echange2);
            Echange echange3 = new Echange(
                    objetService.obtenirObjetParId(10L), objetService.obtenirObjetParId((long) ((Math.random() * numberOfUsers) + 1)),
                    LocalDateTime.now().minusDays(1), Etat.REFUSE, LocalDateTime.now().plusDays(3));
            echangeRepository.save(echange3);
        };
    }


}