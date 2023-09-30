package az.sariyevtech.ecommerce.util;

import az.sariyevtech.ecommerce.dto.authDto.AccessToken;
import az.sariyevtech.ecommerce.exception.CustomException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TokenGenerator {

    @Value("${jwt-variables.KEY}")
    private String KEY;
    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;
    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;

    public String generateToken(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public DecodedJWT verifyJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try {
            return verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Please Try again!", "");
        }
    }

    public String getIdFromToken(AccessToken token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY.getBytes()))
                .build();
        return verifier.verify(token.getToken()).getSubject();
    }


}
