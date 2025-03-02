package fr.knap.testunitaire_esiee.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {

    private String mail;
    private String mdp;

    public Credentials(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    public Credentials() {
    }

}
