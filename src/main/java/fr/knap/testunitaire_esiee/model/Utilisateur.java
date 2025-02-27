    package fr.knap.testunitaire_esiee.model;

    import fr.knap.testunitaire_esiee.respository.Interaction;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.io.*;
    import java.sql.Timestamp;
    import java.util.*;

    /**
     *
     */
    @Entity
    @Getter
    @Setter
    public class Utilisateur extends Interaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String pseudo;
        private String mdp;
        private String mail;
        private String nom;
        private String prenom;

        @OneToMany(mappedBy = "utilisateur")
        private List<Objet> objets;

        public Utilisateur(String pseudo, String mdp, String mail, String nom, String prenom) {
        }

        public Utilisateur() {

        }

        @Override
        public Object ConsulterObjet(Object object) {
            return null;
        }

        @Override
        public List<Objet> RechercheObjet() {
            return List.of();
        }


    }