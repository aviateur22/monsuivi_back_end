package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.dto.LoginDto;
import com.ctoutweb.monsuivi.infra.dto.response.LoginResponseDto;
import com.ctoutweb.monsuivi.infra.model.jwt.JwtGenerated;
import com.ctoutweb.monsuivi.infra.service.AuthService;
import com.ctoutweb.monsuivi.infra.service.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, IJwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        Authentication authentication  = authenticationManager.authenticate(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        JwtGenerated jwt = jwtService.generate(userPrincipal);
        jwtService.saveJwt(userPrincipal.getId(), jwt, loginDto.email());

        return new LoginResponseDto(
                jwt.getJwtToken(),
                userPrincipal.getId(),
                userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                String.format("Bonjour %s", userPrincipal.getNickname()));
    }
}
