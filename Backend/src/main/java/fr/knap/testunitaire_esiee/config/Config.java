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
            Utilisateur user1 = new Utilisateur(
                    "Nicolas",
                    "testnicolas", "nicolas@test.testa",
                    "Test",
                    "Nicolas"
            );

            Utilisateur user2 = new Utilisateur(
                    "Sophie",
                    "sophietest", "sophie@test.com",
                    "Test",
                    "Sophie"
            );

            Utilisateur user3 = new Utilisateur(
                    "Jean",
                    "jeanpierre", "jean.pierre@test.net",
                    "Pierre",
                    "Jean"
            );

            Utilisateur user4 = new Utilisateur(
                    "Laura",
                    "lauratest", "laura@test.org",
                    "Test",
                    "Laura"
            );

            Utilisateur user5 = new Utilisateur(
                    "David",
                    "david123", "david@test.fr",
                    "Test",
                    "David"
            );
            utilisateurRepository.save(user1);
            utilisateurRepository.save(user2);
            utilisateurRepository.save(user3);
            utilisateurRepository.save(user4);
            utilisateurRepository.save(user5);

                Objet objet = new Objet(
                        utilisateurService.obtenirUtilisateurParId(1L), "NVIDIA RTX 4070SUPER", "Carte graphique Nvidia RTX 4070SUPER",
                        CategorieObjet.GAMING, LocalDateTime.now());
                objetRepository.save(objet);

            Objet objet1 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(1L), "Fauteuil IKEA Markus", "Fauteuil de bureau ergonomique avec support lombaire.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet1);

            Objet objet2 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(2L), "Table basse Maison du Monde", "Table basse en bois massif avec rangement intégré.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet2);

            Objet objet3 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(3L), "Tondeuse Bosch Rotak 43", "Tondeuse à gazon électrique avec bac de ramassage.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet3);

            Objet objet4 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(4L), "Arrosoir Gardena 10L", "Arrosoir robuste en métal avec bec verseur précis.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet4);

            Objet objet5 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(5L), "Clavier mécanique Logitech G915", "Clavier mécanique sans fil avec switchs tactiles.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet5);

            Objet objet6 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(6L), "Écran Dell UltraSharp 27\"", "Écran 4K UHD avec technologie IPS.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet6);

            Objet objet7 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(7L), "Manette Xbox Elite Series 2", "Manette pro personnalisable avec paddles.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet7);

            Objet objet8 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(8L), "Casque SteelSeries Arctis Pro", "Casque gaming haute fidélité avec son surround.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet8);

            Objet objet9 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(9L), "Perceuse Bosch GSR 12V", "Perceuse-visseuse sans fil avec batterie lithium-ion.",
                    CategorieObjet.OUTILS, LocalDateTime.now());
            objetRepository.save(objet9);

            Objet objet10 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(10L), "Timbre Napoléon III 1863", "Timbre rare en bon état, valeur collection.",
                    CategorieObjet.COLLECTION, LocalDateTime.now());
            objetRepository.save(objet10);

            Objet objet11 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(11L), "Roman 'Dune' de Frank Herbert", "Édition collector avec couverture rigide.",
                    CategorieObjet.LITTERATURE, LocalDateTime.now());
            objetRepository.save(objet11);

            Objet objet12 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(12L), "Sweat Nike Tech Fleece", "Sweat à capuche gris, taille L.",
                    CategorieObjet.VETEMENTS, LocalDateTime.now());
            objetRepository.save(objet12);

            Objet objet13 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(13L), "Aspirateur Dyson V11", "Aspirateur sans fil puissant avec station d'accueil.",
                    CategorieObjet.ELECTROMENAGER, LocalDateTime.now());
            objetRepository.save(objet13);

            Objet objet14 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(14L), "Porte-clés Marvel", "Porte-clés en métal avec logo Avengers.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet14);

            Objet objet15 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(15L), "Tournevis multifonction Bosch", "Tournevis avec embouts interchangeables.",
                    CategorieObjet.OUTILS, LocalDateTime.now());
            objetRepository.save(objet15);

            Objet objet16 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(16L), "Console PS5", "Console Sony PlayStation 5 en excellent état.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet16);

            Objet objet17 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(17L), "MacBook Pro M2", "Ordinateur portable Apple, 16 Go RAM, 512 Go SSD.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet17);

            Objet objet18 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(18L), "Veste The North Face", "Veste imperméable, idéale pour l'hiver.",
                    CategorieObjet.VETEMENTS, LocalDateTime.now());
            objetRepository.save(objet18);

            Objet objet19 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(19L), "Machine à café Nespresso Vertuo", "Machine avec capsules compatibles Vertuo.",
                    CategorieObjet.ELECTROMENAGER, LocalDateTime.now());
            objetRepository.save(objet19);

            Objet objet20 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(20L), "Lampe Philips Hue", "Lampe connectée avec variation de couleurs.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet20);

            Objet objet21 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(21L), "Grille-pain SMEG", "Grille-pain rétro avec thermostat réglable.",
                    CategorieObjet.ELECTROMENAGER, LocalDateTime.now());
            objetRepository.save(objet21);

            Objet objet22 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(22L), "Tondeuse Husqvarna Automower", "Robot tondeuse autonome.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet22);

            Objet objet23 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(23L), "Préférence d'échange : Roomba", "Aspirateur intelligent recherché en échange.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet23);

            Objet objet24 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(24L), "Sac à dos Eastpak", "Sac à dos noir, capacité 24L.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet24);

            Objet objet25 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(25L), "Jeu Zelda: Tears of the Kingdom", "Jeu Nintendo Switch, édition standard.",
                    CategorieObjet.GAMING, LocalDateTime.now());
            objetRepository.save(objet25);

            Objet objet26 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(26L), "Bureau en bois massif", "Bureau spacieux avec tiroirs intégrés.",
                    CategorieObjet.MOBILIER, LocalDateTime.now());
            objetRepository.save(objet26);

            Objet objet27 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(27L), "Souris Logitech MX Master 3", "Souris sans fil ergonomique pour travail intensif.",
                    CategorieObjet.INFORMATIQUE, LocalDateTime.now());
            objetRepository.save(objet27);

            Objet objet28 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(28L), "Livres Harry Potter édition Gallimard", "Collection complète, bon état.",
                    CategorieObjet.LITTERATURE, LocalDateTime.now());
            objetRepository.save(objet28);

            Objet objet29 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(29L), "Lunettes de soleil Ray-Ban Aviator", "Verres polarisés, monture dorée.",
                    CategorieObjet.AUTRE, LocalDateTime.now());
            objetRepository.save(objet29);

            Objet objet30 = new Objet(
                    utilisateurService.obtenirUtilisateurParId(30L), "Gants de jardinage Wolf-Garten", "Gants résistants pour la taille et plantation.",
                    CategorieObjet.JARDINAGE, LocalDateTime.now());
            objetRepository.save(objet30);

            Echange echange = new Echange(
                    objetService.obtenirObjetParId(9L), objetService.obtenirObjetParId(3L),
                    LocalDateTime.now(), Etat.ATTENTE, null);
            echangeRepository.save(echange);
            Echange echange2 = new Echange(
                    objetService.obtenirObjetParId(8L), objetService.obtenirObjetParId(5L),
                    LocalDateTime.now(), Etat.ACCEPTE, LocalDateTime.now().plusDays(2));
            echangeRepository.save(echange2);
            Echange echange3 = new Echange(
                    objetService.obtenirObjetParId(10L), objetService.obtenirObjetParId(4L),
                    LocalDateTime.now().minusDays(1), Etat.REFUSE, LocalDateTime.now().plusDays(3));
            echangeRepository.save(echange3);
        };
    }
}