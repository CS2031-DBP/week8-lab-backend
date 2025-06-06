package week8.lab.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest servletRequest,
                                 @NonNull HttpServletResponse servletResponse,
                                 @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = servletRequest.getHeader("Authorization");
        String jwt;
        String userEmail;

        if (!StringUtils.hasText(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (StringUtils.hasText(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            jwtService.validateToken(jwt, userEmail);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}