package com.ctoutweb.monsuivi.core.port.getProductDetail;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;

public interface IGetProductDetailGateway {
  /**
   * Vérification si le vendeur peut récupérer les détails d'un produit
   * @param productId long
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerGetProductDetail(long productId, long sellerId);

  /**
   * Récupération des détail d'un produit
   * @return IProductDetail  Detail sur un produit
   */
  IProductDetail getProductDetail();
}
