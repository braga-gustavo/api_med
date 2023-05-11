/**
 * @author :Gustavo
 * Date :05/04/2023
 * Time :16:41
 * Project Name :med
 **/
package med.voll.med.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.med.domain.users.User;
import med.voll.med.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            var alogrithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(tokenExpireDate())
                    .sign(alogrithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating JWT toke", exception);
        }
    }

    public String getSubject(String jwtToken) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT token");
        }
    }


    private Instant tokenExpireDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
