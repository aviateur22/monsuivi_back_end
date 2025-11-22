package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.dto.LoginDto;
import com.ctoutweb.monsuivi.infra.dto.response.LoginResponseDto;
import com.ctoutweb.monsuivi.infra.dto.RegisterSellerDto;
import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;
import com.ctoutweb.monsuivi.infra.dto.response.response.ResponseMessageImpl;
import com.ctoutweb.monsuivi.infra.exception.AuthenicationException;
import com.ctoutweb.monsuivi.infra.exception.RegisterException;
import com.ctoutweb.monsuivi.infra.model.jwt.JwtGenerated;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import com.ctoutweb.monsuivi.infra.service.IAuthService;
import com.ctoutweb.monsuivi.infra.service.IJwtService;
import com.ctoutweb.monsuivi.infra.model.RegisterSeller;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {
    private final ISellerRepository sellerRepository;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;
    private final RegisterSeller registerSeller;

    public AuthServiceImpl(
            ISellerRepository sellerRepository,
            AuthenticationManager authenticationManager,
            IJwtService jwtService,
            RegisterSeller registerSeller) {
        this.sellerRepository = sellerRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.registerSeller = registerSeller;
    }

    @Override
    @Transactional(dontRollbackOn = AuthenicationException.class)
    public LoginResponseDto login(LoginDto loginDto) throws AuthenicationException {
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

    @Override
    @Transactional
    public IResponseMessage registerSeller(RegisterSellerDto dto) throws RegisterException {

        if(!registerSeller.isRegisterEmailAvailable(dto.email()))
            throw new RegisterException(String.format("L'email %s est déja utilisé", dto.email()));

        registerSeller.registerSeller(dto);
        return new ResponseMessageImpl("Félicitation votre compte est créé.");
    }

    @Override
    @Transactional
    public IResponseMessage logout(long sellerId) {
        jwtService.deleteJwtBySellerId(sellerId);
        String logoutMessage = sellerRepository.findById(sellerId)
                .map(seller -> String.format("A bientôt %s", seller.getNickname()))
                .orElse("A bientôt");

        return new ResponseMessageImpl(logoutMessage);
    }
}
