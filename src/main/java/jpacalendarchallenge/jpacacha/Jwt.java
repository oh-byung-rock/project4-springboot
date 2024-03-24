package jpacalendarchallenge.jpacacha;

import com.fasterxml.classmate.AnnotationOverrides;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Jwt {
    private static final String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvZSBCbG9nZ3MiLCJpYXQiOjE1MTYyMzkwMjJ9.QoGc9ZDyWC9_ITusg6TlUx7hAHgV3WfLCrRPrP0IcYU";

    public static String generateToken(String name) {
        return Jwts.builder()
                .claim("name", name)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }

    public static Jws<Claims> validateAndParseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token);
    }
}