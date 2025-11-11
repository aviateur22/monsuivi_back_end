package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.LoginDto;
import com.ctoutweb.monsuivi.infra.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginDto loginDto);
}
