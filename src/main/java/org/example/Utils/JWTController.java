package org.example.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJws;
import org.example.Databases.CrudUserRepository;
import org.example.Models.User;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTController {

    private static final String SECRET_KEY = "VerySecretAndSecuredHiddenKey";

    //one hour for testing
    private static final long EXPIRATION_TIME = 3600 * 1000;

    public static final String ISSUER = "MyHttpServer";

    private static CrudUserRepository users = new CrudUserRepository();

    public static String createJWT(String id, String issuer, String subject) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + EXPIRATION_TIME;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    //Sample method to validate and read the JWT
    public static boolean validateToken(String jwt) {
        if(jwt != null) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                        .parseClaimsJws(jwt).getBody();

                User client = users.getUserByLogin(claims.getId());
                if (client != null) {
                    if ((claims.getSubject().equals(client.getPassword()) && (claims.getIssuer().equals(ISSUER)))) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
