package com.ctoutweb.monsuivi.core.port.desactivateProduct;

import com.ctoutweb.monsuivi.core.entity.product.IUpdateProductActivation;

public interface IUpdateProductActivationGateway {
  boolean canSellerDesactivateProduct(long productId, long sellerId);

  IUpdateProductActivation updateProductActivation(long productId, boolean isProductActivate);

}
