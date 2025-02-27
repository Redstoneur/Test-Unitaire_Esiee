package fr.knap.testunitaire_esiee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 */
public class Objet {

    /**
     * Default constructor
     */
    public Objet() {
    }

    /**
     * 
     */
    private String Nom;

    /**
     * 
     */
    private String Description;

    /**
     * 
     */
    private Timestamp dateCreation;

}