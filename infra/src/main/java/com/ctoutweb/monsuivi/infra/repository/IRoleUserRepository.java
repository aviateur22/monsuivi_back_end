package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.RoleSellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleUserRepository extends JpaRepository<RoleSellerEntity, Long> {
}
