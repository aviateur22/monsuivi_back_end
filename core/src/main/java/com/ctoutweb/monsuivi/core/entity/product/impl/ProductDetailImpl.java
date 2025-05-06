package com.ctoutweb.monsuivi.core.entity.product.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;

import java.time.LocalDate;
public record ProductDetailImpl(
        long productid,
        String imagePath,
        double productPurchasePrice,
        String productName,
        LocalDate productBuyDay,
        LocalDate productSoldDay,
        double productSoldPrice,
        String productStatus

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
}
