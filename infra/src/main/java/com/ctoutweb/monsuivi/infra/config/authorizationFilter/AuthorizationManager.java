package com.ctoutweb.monsuivi.infra.config.authorizationFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipalAuthenticationToken;
import com.ctoutweb.monsuivi.infra.exception.AuthenicationException;
import com.ctoutweb.monsuivi.infra.exception.AuthorizationException;
import com.ctoutweb.monsuivi.infra.repository.IRoleUserRepository;
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
    private final IRoleUserRepository roleUserRepository;

    public AuthorizationManager(IJwtService jwtService, ISellerRepository sellerRepository, IRoleUserRepository roleUserRepository) {
        this.jwtService = jwtService;
        this.sellerRepository = sellerRepository;
        this.roleUserRepository = roleUserRepository;
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
    private String extractBearerFromRequest(HttpServletRequest request) throws AuthorizationException {
        var token = request.getHeader("authorization");
        if(!StringUtils.hasText(token) || !token.startsWith("Bearer "))
            throw new AuthorizationException("");

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
            DecodedJWT decodedJWT = jwtService.validateAndDecode(bearer);
            long sellerId = decodedJWT.getClaim("id").asLong();

            SellerEntity requestSeller = sellerRepository.findById(sellerId).orElseThrow(() -> new AuthenicationException(""));

            String email = requestSeller.getEmail();
            List<SimpleGrantedAuthority> grantedAuthorities = requestSeller.getRoles().stream()
                    .map(RoleSellerEntity::getRole)
                    .map(RoleEntity::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            return new UserPrincipal(sellerId, email, grantedAuthorities);
        } catch (Exception exception) {
            throw new AuthorizationException("Votre session n'est plus valide, merci de vous reconnecter");

        }
    }
}
