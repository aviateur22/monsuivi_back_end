package com.ctoutweb.monsuivi.infra.constant;

public class LoginConstant {

    private LoginConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final int LOGIN_DELAY_IN_MINUTE = 5;
    public static final int FAILED_LOGIN_ATTEMPT_AVAILABLE = 4;
    public static final int USER_LOGIN_COUNT = 4;
}
