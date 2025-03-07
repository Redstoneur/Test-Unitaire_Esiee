package fr.knap.testunitaire_esiee.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class representing user credentials.
 */
@Getter
@Setter
public class Credentials {

    /**
     * The email of the user.
     */
    private String mail;

    /**
     * The password of the user.
     */
    private String mdp;

    /**
     * Constructs a new Credentials object with the specified email and password.
     *
     * @param mail The email of the user.
     * @param mdp The password of the user.
     */
    public Credentials(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    /**
     * Default constructor for Credentials.
     */
    public Credentials() {
    }
}