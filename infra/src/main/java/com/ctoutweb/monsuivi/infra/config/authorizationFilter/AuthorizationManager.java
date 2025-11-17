package com.ctoutweb.monsuivi.infra.config.authorizationFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipalAuthenticationToken;
import com.ctoutweb.monsuivi.infra.exception.AuthenicationException;
import com.ctoutweb.monsuivi.infra.exception.AuthorizationException;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.RoleEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.RoleSellerEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import com.ctoutweb.monsuivi.infra.service.IJwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class AuthorizationManager {
    private final IJwtService jwtService;
    private final ISellerRepository sellerRepository;

    public AuthorizationManager(IJwtService jwtService, ISellerRepository sellerRepository) {
        this.jwtService = jwtService;
        this.sellerRepository = sellerRepository;
    }

    /**
     * Gestion des authorization de la requete
     */
    @Transactional
    public void manageRequestAuthorization(HttpServletRequest request) {
        String bearer = extractBearerFromRequest(request);
        UserPrincipal requestSeller = getSellerFromBearer(bearer);

        SecurityContextHolder.getContext().setAuthentication(new UserPrincipalAuthenticationToken(requestSeller));
    }

    /**
     * Extraction du bearer token de la requête
     *
     * @return Le bearer token
     *
     * @throws AuthorizationException Erreur sur le bearer token
     */
    private String extractBearerFromRequest(HttpServletRequest request) throws AuthenicationException {
        var token = request.getHeader("authorization");
        if(!StringUtils.hasText(token) || !token.startsWith("Bearer "))
            throw new AuthenicationException("");

        return token.substring(7);
    }

    /**
     * Récupération du seller à partir du bearer token
     *
     * @param bearer Le token d'authorization extrait des headers
     *
     * @return Le vendeur faisant la requête
     *
     * @throws AuthorizationException Erreur d'authentification sur le bearer token
     */
    private UserPrincipal getSellerFromBearer(String bearer) throws AuthenicationException {
        try {
            DecodedJWT decodedJWT = jwtService.validateAndDecode(bearer).orElseThrow(() ->
                    new AuthenicationException("")
            );

            long sellerId = decodedJWT.getClaim("id").asLong();
            String jwtId = decodedJWT.getClaim("jti").asString();

            if(!jwtService.isJwtUuidValid(jwtId))
                throw new AuthenicationException("");

            SellerEntity requestSeller = sellerRepository.findById(sellerId).orElseThrow(() ->
                    new AuthenicationException("")
            );

            List<SimpleGrantedAuthority> grantedAuthorities = requestSeller
                    .getRoles()
                    .stream()
                    .map(RoleSellerEntity::getRole)
                    .map(RoleEntity::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            return new UserPrincipal(sellerId, requestSeller.getEmail(), grantedAuthorities);
        } catch (AuthenicationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new AuthenicationException("Votre session n'est plus valide, merci de vous reconnecter");
        }
    }
}
