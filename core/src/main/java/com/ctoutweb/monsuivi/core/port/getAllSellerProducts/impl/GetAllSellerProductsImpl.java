package com.ctoutweb.monsuivi.core.port.getAllSellerProducts.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;

import java.util.List;

public record GetAllSellerProductsImpl(
        String responseMessage,
        List<IProductSummarize> products
) implements IGetAllProductsOutput {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }

  @Override
  public List<IProductSummarize> getAllProducts() {
    return products;
  }

  @Override
  public long getProductQuantity() {
    return products.size();
  }
}
