package com.ctoutweb.monsuivi.core.port.getProductDetail.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailOutput;

public record GetProductDetailOutputImpl(IProductDetail productDetail, long sellerId, String responseMessage) implements IGetProductDetailOutput {
  @Override
  public IProductDetail getProductDetail() {
    return productDetail;
  }

  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
