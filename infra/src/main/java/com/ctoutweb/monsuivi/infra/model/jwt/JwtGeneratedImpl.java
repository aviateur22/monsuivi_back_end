package com.ctoutweb.monsuivi.infra.model.jwt;

import java.time.ZonedDateTime;

public record JwtGeneratedImpl(String jwtId, String jwtToken, ZonedDateTime expiredAt) implements JwtGenerated {
    @Override
    public String getJwtId() {
        return jwtId;
    }

    @Override
    public String getJwtToken() {
        return jwtToken;
    }

    @Override
    public ZonedDateTime getExpiredAt() {
        return expiredAt;
    }
}
