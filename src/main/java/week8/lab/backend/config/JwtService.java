package week8.lab.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import week8.lab.backend.account.domain.AccountService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.signing.key}")
    private String secret;

    private final AccountService accountService;

    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 10);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    public void validateToken(String token, String userEmail) throws AuthenticationException {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        UserDetails userDetails = accountService.loadUserByUsername(userEmail);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                token,
                userDetails.getAuthorities()
        );
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }
}
