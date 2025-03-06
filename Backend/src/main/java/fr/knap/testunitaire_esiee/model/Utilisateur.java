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

        @Column(nullable = false, unique = true)
        private String pseudo;
        private String mdp;

        @Column(nullable = false, unique = true)
        private String mail;
        private String nom;
        private String prenom;

        @OneToMany(mappedBy = "utilisateur")
        private List<Objet> objets;

        public Utilisateur(String pseudo, String mdp, String mail, String nom, String prenom) {
            this.pseudo = pseudo;
            this.mdp = mdp;
            this.mail = mail;
            this.nom = nom;
            this.prenom = prenom;
            this.objets = new ArrayList<>();
        }
        public Utilisateur(String pseudo, String mdp, String mail, String nom, String prenom, List<Objet> objets) {
            this.pseudo = pseudo;
            this.mdp = mdp;
            this.mail = mail;
            this.nom = nom;
            this.prenom = prenom;
            this.objets = objets;
        }

        public Utilisateur() {
        }

    }