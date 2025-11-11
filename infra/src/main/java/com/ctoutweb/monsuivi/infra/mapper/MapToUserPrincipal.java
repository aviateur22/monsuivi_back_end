package com.ctoutweb.monsuivi.infra.mapper;

import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import org.springframework.stereotype.Component;

@Component
public class MapToUserPrincipal {
    /**
     * Map un SellerEntity en UserPrincipal
     *
     * @param user UserEntity user - Personne que se connecte
     * @return UserPrincipal
     */
    public UserPrincipal map(SellerEntity user, String plainTextPassword) {
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                plainTextPassword,
                user.getPassword(),
                user.getRoles().stream().map(roleSeller -> roleSeller.getRole().getRole()).toList(),
                user.getSellerAccount().getAccountActive()
        );
    }
}
