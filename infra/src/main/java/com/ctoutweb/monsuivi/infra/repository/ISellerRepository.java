package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISellerRepository extends JpaRepository<SellerEntity, Long> {


    /**
     * Recherche vendeur par son email
     *
     * @param email Email du vendeur qui est recherché
     *
     * @return Un Optional du vendeur ou empty.optional
     */
    Optional<SellerEntity> findByEmail(String email);
}
