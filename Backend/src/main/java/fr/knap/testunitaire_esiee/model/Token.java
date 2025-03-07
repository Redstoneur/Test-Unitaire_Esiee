package fr.knap.testunitaire_esiee.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Key;
import java.util.Date;

/**
 * Entity class representing a token.
 */
@Entity
@Getter
@Setter
public class Token {

    /**
     * The unique identifier for the token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The token string.
     */
    @Column(nullable = false, unique = true)
    private String token;

    /**
     * The expiration date of the token.
     */
    @Column(nullable = false)
    private Date expirationDate;

    /**
     * The key used for signing the token.
     */
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Constructs a new Token object with the specified email and password.
     * Generates a token string and sets the expiration date to 1 hour from the current time.
     *
     * @param mail The email of the user.
     * @param mdp  The password of the user.
     */
    public Token(String mail, String mdp) {
        this.expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 hour expiration
        this.token = Jwts.builder()
                .setSubject(mail + mdp)
                .setIssuedAt(new Date())
                .setExpiration(this.expirationDate)
                .signWith(key)
                .compact();
    }

    /**
     * Default constructor for Token.
     */
    public Token() {
    }

    /**
     * Validates the specified token.
     *
     * @param token The token to validate.
     * @return true if the token is valid, false otherwise.
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the subject from the specified token.
     *
     * @param token The token from which to retrieve the subject.
     * @return The subject of the token.
     */
    public static String getSubject(String token) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Retrieves the email from the specified token.
     *
     * @param token The token from which to retrieve the email.
     * @return The email of the token.
     */
    public static String getEmail(String token) {
        return getSubject(token).split(" ")[0];
    }

    /**
     * Disconnects the token by setting its expiration date to the current time.
     */
    public void disconnect() {
        this.expirationDate = new Date();
    }
}