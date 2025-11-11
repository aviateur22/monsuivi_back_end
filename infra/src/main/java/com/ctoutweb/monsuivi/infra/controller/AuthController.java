package com.ctoutweb.monsuivi.infra.controller;

import com.ctoutweb.monsuivi.infra.annotation.DtoValidator;
import com.ctoutweb.monsuivi.infra.dto.LoginDto;
import com.ctoutweb.monsuivi.infra.dto.response.LoginResponseDto;
import com.ctoutweb.monsuivi.infra.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/auth")
public class AuthController {
    private final AuthService authService;
    private final DtoValidator dtoValidator;

    public AuthController(AuthService authService, DtoValidator dtoValidator) {
        this.authService = authService;
        this.dtoValidator = dtoValidator;
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        dtoValidator.validateDto(loginDto);

        LoginResponseDto loginResponseDto = authService.login(loginDto);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
