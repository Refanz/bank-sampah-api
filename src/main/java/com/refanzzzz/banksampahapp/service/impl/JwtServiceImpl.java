package com.refanzzzz.banksampahapp.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import com.refanzzzz.banksampahapp.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${trash.app.jwt-secret}")
    private String SECRET_KEY;

    @Value("${trash.app.jwt-exp-in-minutes-access-token}")
    private Long JWT_EXP_IN_MINUTES_ACCESS_TOKEN;

    @Value("${trash.app.jwt-issuer}")
    private String JWT_ISSUER;

    @Override
    public String generateAccessToken(UserAccount userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(JWT_EXP_IN_MINUTES_ACCESS_TOKEN, ChronoUnit.MINUTES))
                    .withSubject(userAccount.getId())
                    .withClaim("role", userAccount.getRole().getName())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            log.error("Error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating JWT Token");
        }
    }

    @Override
    public String getUserId(String token) {
        DecodedJWT decodedJWT = extractClaimJWT(token);

        if (decodedJWT != null)
            return decodedJWT.getSubject();

        return null;
    }

    @Override
    public String extractTokenFromRequest(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        return parseToken(bearerToken);
    }

    private DecodedJWT extractClaimJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();

            return jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("Error while validate JWT Token: {}", e.getMessage());
            return null;
        }
    }

    private String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);

        return null;
    }
}
