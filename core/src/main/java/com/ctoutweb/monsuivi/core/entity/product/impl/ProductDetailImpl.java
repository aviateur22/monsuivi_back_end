package com.ctoutweb.monsuivi.core.entity.product.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;

import java.time.LocalDate;
public record ProductDetailImpl(
        long productid,
        String imagePath,
        Double productPurchasePrice,
        String productName,
        LocalDate productBuyDay,
        LocalDate productSoldDay,
        Double productSoldPrice,
        String productStatus,
        String productCategoryCode

) implements IProductDetail {
  @Override
  public long getProductId() {
    return productid;
  }

  @Override
  public String getProductImagePath() {
    return imagePath;
  }

  @Override
  public Double getProductPurchasePrice() {
    return productPurchasePrice;
  }

  @Override
  public String getProductName() {
    return productName;
  }

  @Override
  public LocalDate getProductBuyAt() {
    return productBuyDay;
  }

  @Override
  public LocalDate getProductSoldAt() {
    return productSoldDay;
  }

  @Override
  public Double getProductSoldPrice() {
    return productSoldPrice;
  }

  @Override
  public String getProductStatus() {
    return productStatus;
  }

  @Override
  public String getProductCategoryCode() {
    return productCategoryCode;
  }
}
