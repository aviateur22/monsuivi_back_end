package com.ctoutweb.monsuivi.infra.model;

import com.ctoutweb.monsuivi.infra.dto.RegisterSellerDto;
import com.ctoutweb.monsuivi.infra.repository.ISellerAccountRepository;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.repository.IRoleUserRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.RoleEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.RoleSellerEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerAccountEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import com.ctoutweb.monsuivi.infra.service.ICryptoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class RegisterSeller {
    private final ICryptoService cryptoService;
    private final ISellerRepository sellerRepository;
    private final ISellerAccountRepository sellerAccountRepository;
    private final IRoleUserRepository roleUserRepository;

    @Value("${zone.id}")
    String zoneId;

    public RegisterSeller(
            ICryptoService cryptoService,
            ISellerRepository sellerRepository,
            ISellerAccountRepository sellerAccountRepository, IRoleUserRepository roleUserRepository) {
        this.cryptoService = cryptoService;
        this.sellerRepository = sellerRepository;
        this.sellerAccountRepository = sellerAccountRepository;
        this.roleUserRepository = roleUserRepository;
    }

    /**
     * Vérification disponibilité de l'email
     *
     * @param email Le nouvel email a vérifier si pas deja present en base
     *
     * @return True si email disponible
     */
    public boolean isRegisterEmailAvailable(String email) {
        return sellerRepository.findByEmail(email).isEmpty();
    }

    /**
     * Enregistrement du nouvel utulisateur
     *
     * @param dto Les données du nouveau sller
     */
    public void registerSeller(RegisterSellerDto dto) {
        String hashPassword = cryptoService.hashText(dto.password());
        SellerEntity sellerToRegister = new SellerEntity(dto.email(), hashPassword, dto.nickname());
        sellerToRegister = sellerRepository.save(sellerToRegister);

        // Creation du compte utilisateur
        createAndActivateSellerAccount(sellerToRegister);

        // Creation des roles utilisateurs
        createSellerRole(sellerToRegister);
    }

    /**
     * Creation du compte utilisateur
     *
     * @param seller Les données du seller nécessaire à la creation du compte
     */
    public void createAndActivateSellerAccount(SellerEntity seller) {
        final boolean IS_ACCOUNT_ACTIVE = true;
        final ZonedDateTime ACTIVATE_ACOUNT_DATE = LocalDateTime.now().atZone(ZoneId.of(zoneId));

        SellerAccountEntity sellerAccountToCreate = new SellerAccountEntity(seller, IS_ACCOUNT_ACTIVE, ACTIVATE_ACOUNT_DATE);
        sellerAccountRepository.save(sellerAccountToCreate);
    }

    /**
     * Creation du role Seller pour le nouvel utilisateur
     *
     * @param seller Les données du seller nécessaire à la creation du role
     */
    public void createSellerRole(SellerEntity seller) {
        final int SELLER_ROLE_ID = 1;
        RoleEntity sellerRole = new RoleEntity(SELLER_ROLE_ID);

        RoleSellerEntity roleSellerToCreate = new RoleSellerEntity(seller, sellerRole);
        roleUserRepository.save(roleSellerToCreate);
    }
}
