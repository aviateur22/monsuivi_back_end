package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<ImageEntity, Long> {
}
