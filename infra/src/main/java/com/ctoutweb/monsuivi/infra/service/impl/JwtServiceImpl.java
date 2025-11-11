package com.ctoutweb.monsuivi.infra.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.model.jwt.JwtGenerated;
import com.ctoutweb.monsuivi.infra.model.jwt.JwtGeneratedImpl;
import com.ctoutweb.monsuivi.infra.repository.IJwtRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.JwtEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import com.ctoutweb.monsuivi.infra.service.IJwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements IJwtService {
    @Value("${jwt.validity.hour}")
    Long jwtValidity;
    @Value("${jwt.secret.key}")
    String jwtSecret;
    @Value("${jwt.issuer}")
    String jwtIssuer;
    @Value("${zone.id}")
    String zoneId;
    private final IJwtRepository jwtRepository;

    public JwtServiceImpl(IJwtRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    @Override
    public JwtGenerated generate(UserPrincipal userPrincipal) {

        Instant expiredAt = Instant.now().plus(Duration.ofHours(jwtValidity));
        byte[] timeNow = ("time now" +" " + System.currentTimeMillis()).getBytes();
        String jwtId = UUID.nameUUIDFromBytes(timeNow).toString();

        List<String> authorities = userPrincipal.getAuthorities()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withJWTId(jwtId)
                .withIssuer(jwtIssuer)
                .withExpiresAt(expiredAt)
                .withClaim("userId", userPrincipal.getId())
                .withClaim("authorities", authorities)
                .sign(Algorithm.HMAC256(jwtSecret));

        return new JwtGeneratedImpl(jwtId, token, expiredAt.atZone(ZoneId.of(zoneId)));
    }

    @Override
    public DecodedJWT validateAndDecode(String token) {
        return null;
    }

    @Override
    public void deleteJwtByUserEmail(String email) {

    }

    @Override
    public void saveJwt(Long userId, JwtGenerated jwt, String email) {
        SellerEntity userLogin = new SellerEntity();
        userLogin.setId(userId);

        JwtEntity insertJwtLogin = new JwtEntity();
        insertJwtLogin.setJwtToken(jwt.getJwtToken());
        insertJwtLogin.setJwtId(jwt.getJwtId());
        insertJwtLogin.setSeller(userLogin);
        insertJwtLogin.setExpiredAt(jwt.getExpiredAt());
        insertJwtLogin.setEmail(email);
        insertJwtLogin.setIsValid(true);

        jwtRepository.save(insertJwtLogin);
    }
}
