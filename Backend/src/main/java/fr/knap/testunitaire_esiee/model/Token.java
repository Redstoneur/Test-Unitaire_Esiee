package fr.knap.testunitaire_esiee.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import java.security.Key;
import java.util.Date;

@Getter
public class Token {

    private String token;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Token(String mail, String mdp) {
        this.token = Jwts.builder()
                .setSubject(mail+mdp)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(key)
                .compact();
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
    this.token = null;
}
}