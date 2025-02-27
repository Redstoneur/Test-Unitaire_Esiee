package fr.knap.testunitaire_esiee.model;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 */
public class Utilisateur extends Interaction {

    /**
     * Default constructor
     */
    public Utilisateur() {
    }

    /**
     * 
     */
    private String Pseudo;

    /**
     * 
     */
    private String MDP;

    /**
     * 
     */
    private String Mail;

    /**
     * 
     */
    private String Nom;

    /**
     * 
     */
    private String Prénom;

    /**
     * @param nom 
     * @param description 
     * @param dateTime
     * @return
     */
    public Objet CréerObjet(String nom, String description, Timestamp dateTime) {
        // TODO implement here
        return null;
    }

    /**
     * @param echange 
     * @param proposition 
     * @return
     */
    public Echange ProposerUnEchange(Objet echange, Objet proposition) {
        // TODO implement here
        return null;
    }

    /**
     * @param etat 
     * @return
     */
    public void RepondreEchange(Etat etat) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Echange ConsulterEchange() {
        // TODO implement here
        return null;
    }

    /**
     * @param objet
     * @return
     */
    public Objet ConsulterObjet(Object objet) {
        return null;
    }

    /**
     * @return
     */
    public List<Objet> RechercheObjet() {
        return null;
    }

}