package com.ctoutweb.monsuivi.core.port.common;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;

import java.util.List;

public interface ISellerProductsManagerGateway {
  boolean isSellerFind(long sellerId);
  List<IProductDetail> getAllSellerProducts(long sellerId);
}
