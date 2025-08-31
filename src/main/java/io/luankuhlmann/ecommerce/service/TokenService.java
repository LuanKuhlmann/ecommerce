package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.core.enumerated.TokenType;
import io.luankuhlmann.ecommerce.dto.response.TokenResponse;
import io.luankuhlmann.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${jwt.expires-access}")
    private Long expiresInAccess;

    @Value("${jwt.expires-refresh}")
    private Long expiresInRefresh;

    public TokenResponse createAccessToken(User user) {
        JwtClaimsSet claims = buildClaims(user, TokenType.ACCESS);
        String tokenValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new TokenResponse(tokenValue, claims.getExpiresAt().getEpochSecond());
    }

    public TokenResponse createRefreshToken(User user) {
        JwtClaimsSet claims = buildClaims(user, TokenType.REFRESH);
        String tokenValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new TokenResponse(tokenValue, claims.getExpiresAt().getEpochSecond());
    }


    private JwtClaimsSet buildClaims(User user, TokenType type) {
        String jti = UUID.randomUUID().toString();

        Instant now = Instant.now();
        Instant expiresAt;

        if(type == TokenType.REFRESH){
            expiresAt = now.plusSeconds(expiresInRefresh);
        } else {
            expiresAt = now.plusSeconds(expiresInAccess);
        }

        JwtClaimsSet.Builder builder = JwtClaimsSet.builder()
                .id(jti)
                .issuer(issuer)
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(expiresAt);

        if (type == TokenType.ACCESS) {
            assignRoles(builder, user);
        }

        return builder.build();
    }

    private void assignRoles(JwtClaimsSet.Builder builder, User user) {
        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName().name())
                .collect(Collectors.toList());

        builder.claim("roles", roles);
    }

}
