package com.ctoutweb.monsuivi.core.port.filterSellerProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;

import java.util.List;

public interface IFilterSellerProductsGateway {
  boolean isSellerFind(long sellerId);
  List<IProductDetail> getAllSellerProducts(long sellerId);
}
