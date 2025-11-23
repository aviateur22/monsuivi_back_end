package com.ctoutweb.monsuivi.infra.config.authentication;

import java.time.ZonedDateTime;

public record LoginStatus(boolean isLoginAuthorized, String loginErrorMessage) {
}
