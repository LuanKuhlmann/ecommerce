package io.luankuhlmann.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class TokenUtil {

    private TokenUtil() {
    }

    public static String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token ausente ou inválido");
        }
        return authorizationHeader.substring(7);
    }

    public static Long extractUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth && jwtAuth.isAuthenticated()) {
            Jwt jwt = jwtAuth.getToken();

            String subject = jwt.getSubject();
            if (subject != null && !subject.isEmpty()) {
                try {
                    return Long.valueOf(subject);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Subject do token não é um número válido", e);
                }
            }
            throw new RuntimeException("Token com ausência de subject");
        }
        throw new RuntimeException("Nenhum token JWT autenticado no contexto de segurança");
    }
}
