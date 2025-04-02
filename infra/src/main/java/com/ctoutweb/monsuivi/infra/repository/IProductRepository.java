package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
}
