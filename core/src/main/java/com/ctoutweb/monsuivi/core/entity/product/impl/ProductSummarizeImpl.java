package com.ctoutweb.monsuivi.core.entity.product.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;

public record ProductSummarizeImpl(
        String imagePath,
        String productName,
        String statusCode,
        String categorycode) implements IProductSummarize {
  @Override
  public String getProductImagePath() {
    return imagePath;
  }

  @Override
  public String getProductName() {
    return productName;
  }

  @Override
  public String getProductStatusCode() {
    return statusCode;
  }

  @Override
  public String getProductCategoryCode() {
    return categorycode;
  }
}
