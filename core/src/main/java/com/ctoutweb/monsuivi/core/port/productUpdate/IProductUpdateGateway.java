package com.ctoutweb.monsuivi.core.port.productUpdate;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;

public interface IProductUpdateGateway {
  /**
   * Vérification si le vendeur peut modifier les détails d'un produit
   * @param productId long
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerUpdateProduct(long productId, long sellerId);

  /**
   * Mise à jour d'un produit
   * @param product IProductUpdateInput
   * @return IProductDetail - renvoie les détails d'un produit
   */
  IProductDetail updateProduct(IProductUpdateInput product);
}
