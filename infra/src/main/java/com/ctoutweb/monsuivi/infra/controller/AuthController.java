package com.ctoutweb.monsuivi.infra.controller;

import com.ctoutweb.monsuivi.infra.annotation.DtoValidator;
import com.ctoutweb.monsuivi.infra.dto.LoginDto;
import com.ctoutweb.monsuivi.infra.dto.RegisterSellerDto;
import com.ctoutweb.monsuivi.infra.dto.response.LoginResponseDto;
import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;
import com.ctoutweb.monsuivi.infra.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/auth")
public class AuthController {
    private final IAuthService authService;
    private final DtoValidator dtoValidator;

    public AuthController(IAuthService authService, DtoValidator dtoValidator) {
        this.authService = authService;
        this.dtoValidator = dtoValidator;
    }

    @PostMapping("/register")
    ResponseEntity<IResponseMessage> login(@RequestBody RegisterSellerDto dto) {
        dtoValidator.validateDto(dto);

        IResponseMessage responseMessage = authService.registerSeller(dto);

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        dtoValidator.validateDto(loginDto);

        LoginResponseDto loginResponseDto = authService.login(loginDto);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout/{sellerId}")
    ResponseEntity<IResponseMessage> logout(@PathVariable("sellerId") long sellerId) {
        IResponseMessage logutMessage = authService.logout(sellerId);
        return new ResponseEntity<>(logutMessage, HttpStatus.OK);
    }

}
