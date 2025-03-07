package fr.knap.testunitaire_esiee.dto;

    public class UtilisateurDTO {
        private String pseudo;
        private String nom;
        private String prenom;

        public UtilisateurDTO(String pseudo, String nom, String prenom) {
            this.pseudo = pseudo;
            this.nom = nom;
            this.prenom = prenom;
        }

        public UtilisateurDTO(String pseudo) {
            this.pseudo = pseudo;
        }

        // Getters and setters
        public String getPseudo() {
            return pseudo;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    }