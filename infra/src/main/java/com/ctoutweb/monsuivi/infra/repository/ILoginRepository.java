package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.config.authentication.UserPrincipal;
import com.ctoutweb.monsuivi.infra.repository.entity.LoginEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoginRepository extends JpaRepository<LoginEntity, Long> {
    /**
     * Récupération de toutes les connexions d'un client ordonnées de la connexion la plus récente à la plus vieille
     *
     * @param userId L'identtifiant de la personne en cours de connexion
     *
     * @return Une liste de toutes les connexions de l'utilisateur qui se connecte
     */
    List<LoginEntity> findBySellerOrderByLoginAtDesc(SellerEntity seller);
}
