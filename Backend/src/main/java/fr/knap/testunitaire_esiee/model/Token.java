package fr.knap.testunitaire_esiee.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Key;
import java.util.Date;

@Entity
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Date expirationDate;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Token(String mail, String mdp) {
        this.token = Jwts.builder()
                .setSubject(mail + mdp)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(key)
                .compact();
        this.expirationDate = new Date(System.currentTimeMillis() + 3600000);
    }

    public Token() {
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSubject(String token) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public void disconnect() {
        this.expirationDate = new Date();
    }
}