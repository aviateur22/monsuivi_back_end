package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoginRepository extends JpaRepository<LoginEntity, Long> {
}
