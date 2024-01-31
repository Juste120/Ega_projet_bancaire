package ega.backend.ega.securite;

import ega.backend.ega.entites.Utilisateur;
import ega.backend.ega.services.UtilisateurService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.builder;

@AllArgsConstructor
@Service
public class JwtService {
    private final String ENCRIPTION_KEY ="e2d3df6dda12e0c75e9d12c720a70e2fd9235076e3f540d280a4d28abdf7798e";
    private final UtilisateurService utilisateurService;


    public String extraxtUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }
    public boolean isTokenExpired(String token){
        Date expirationDate = getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

//    private Date getExpirationDateFromToken(String token) {
//        return this.getClain(token, Claims::getExpiration);
//    }
    private <T> T getClaim(String token, Function<Claims, T> function){
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
               .setSigningKey(this.getKey())
               .build()
               .parseClaimsJws(token)
               .getBody();

    }


    public Map<String, String> generate(String username){
        Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);
        return this.generateJwt(utilisateur);
    }
    private Map<String, String> generateJwt(Utilisateur utilisateur) {
        final long currentTime = System.currentTimeMillis();
       final long expirationTime = currentTime + 30 * 60 * 1000;
        final Map<String, Object> clains = Map.of(
                "nom", utilisateur.getNom(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, utilisateur.getEmail()
        );
        final String bearer = builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(utilisateur.getEmail())
                .setClaims(clains)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer", bearer);
    }



    private Key getKey(){
        final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor( decoder);
    }


}
