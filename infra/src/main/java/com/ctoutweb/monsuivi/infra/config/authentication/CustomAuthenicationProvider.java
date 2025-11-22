package com.ctoutweb.monsuivi.infra.config.authentication;

import com.ctoutweb.monsuivi.infra.exception.AuthenicationException;
import com.ctoutweb.monsuivi.infra.mapper.MapToUserPrincipal;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.service.LoginManagerService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuthenicationProvider implements AuthenticationProvider {
    private final ISellerRepository sellerRepository;
    private final MapToUserPrincipal mapToUserPrincipal;

    private final LoginManagerService loginManagerService;

    public CustomAuthenicationProvider(
            ISellerRepository sellerRepository,
            MapToUserPrincipal mapToUserPrincipal,
            LoginManagerService loginManagerService) {
        this.sellerRepository = sellerRepository;
        this.mapToUserPrincipal = mapToUserPrincipal;
        this.loginManagerService = loginManagerService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String plainTextPasswordSendByUser = authentication.getCredentials().toString();
        String emailSendByUser = authentication.getName();

        UserPrincipal userPrincipal = findUser(emailSendByUser, plainTextPasswordSendByUser)
                .orElseThrow(() -> new AuthenicationException("Cet email n'existe pas"));

        long loginUserId = userPrincipal.getId();
        String hashPassword = userPrincipal.getHashPassword();

        LoginStatus loginStatus = loginManagerService.isUserLoginAuthorized(loginUserId);

        if(!loginStatus.isLoginAuthorized())
            throw new AuthenicationException(loginStatus.loginErrorMessage());

        loginStatus = loginManagerService.manageUserLogin(loginUserId, plainTextPasswordSendByUser, hashPassword);

        if(!loginStatus.isLoginAuthorized())
            throw new AuthenicationException(loginStatus.loginErrorMessage());

        return new UsernamePasswordAuthenticationToken(userPrincipal, authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);

    }

    private Optional<UserPrincipal> findUser(String email, String plainTextPassword) {
        return sellerRepository
                .findByEmail(email)
                .map(seller -> mapToUserPrincipal.map(seller, plainTextPassword));
    }
}
