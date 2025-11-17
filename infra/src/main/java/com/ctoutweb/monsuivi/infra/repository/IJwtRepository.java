package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.JwtEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJwtRepository extends JpaRepository<JwtEntity, Long> {
    /**
     * Suppression de tous les JWT liée a un identifiant vendeur
     *
     * @param seller Le vendeur dont on supprime le JWT
     */
    @Transactional
    @Modifying
    void deleteBySeller(SellerEntity seller);

    /**
     * Recherche d'un JWT par son identifiant
     *
     * @param jwtId L'identifiant du JWT a trouver
     *
     * @return Optional Les données du JWT ou optional.empty
     */
    Optional<JwtEntity> findFirstByJwtId(String jwtId);
}
