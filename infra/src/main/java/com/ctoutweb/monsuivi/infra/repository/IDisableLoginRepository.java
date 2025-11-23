package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.DisableLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDisableLoginRepository extends JpaRepository<DisableLoginEntity, Long> {
    /**
     * Recherche si une une connexion est désactivé pour un utilsateur en cours de connexion
     *
     * @param id L'indentifiant de l'utilisateur se connectant
     *
     * @return Renvoie un optional des informations sur une connexion désactivé
     *          ou
     *          Un optional de empty si l'utilsateur n'a pas sa connexion de désactivée
     */
    Optional<DisableLoginEntity> findFirstBySellerId(Long id);
}
