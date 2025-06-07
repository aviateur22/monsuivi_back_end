package com.ctoutweb.monsuivi.infra.repository;

import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long>, IChartRepository {
  /**
   * Recherche d'une liste de produit actif appartenant a un vendeur
   * @param seller SellerEntity - Identifiant vendeur
   * @return List<ProductEntity>
   */
  List<ProductEntity> findByIsActifTrueAndSellerOrderByProductBuyAtDesc(SellerEntity seller);

  /**
   * Recherche d'un produit par product id et seller id
   * @param productId long
   * @param seller Selleterentity
   * @return Optional<ProductEntity>
   */
  Optional<ProductEntity>findByIdAndSeller(long productId, SellerEntity seller);

  /**
   * Recherche d'un produit actif par product id et seller id
   * @param productId long
   * @param seller Selleterentity
   * @return Optional<ProductEntity>
   */
  Optional<ProductEntity>findByIdAndSellerAndIsActifTrue(long productId, SellerEntity seller);

}
