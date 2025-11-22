package com.ctoutweb.monsuivi.core.port.filterSellerProducts.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsOutput;

import java.util.List;

public record FilterSellerProductsOutputImpl(
        String responseMessage,
        List<IProductSummarize> productFilteredList,
        long prroductQuantity
) implements IFilterSellerProductsOutput {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }

  @Override
  public List<IProductSummarize> getSellerFilterProducts() {
    return productFilteredList;
  }

  @Override
  public long getProductQuantity() {
    return prroductQuantity;
  }
}
