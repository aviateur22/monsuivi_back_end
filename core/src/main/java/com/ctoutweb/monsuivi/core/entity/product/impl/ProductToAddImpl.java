package com.ctoutweb.monsuivi.core.entity.product.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductToAdd;

import java.io.File;
import java.time.ZonedDateTime;

public record ProductToAddImpl(
        File image,
        double purchasePrice,
        String productName,
        String productCategoryCode,
        String productStatusCode,
        ZonedDateTime productCreationDate
) implements IProductToAdd {
  @Override
  public File getProductImage() {
    return image;
  }

  @Override
  public Double getProductPurchasePrice() {
    return purchasePrice;
  }

  @Override
  public String getProductName() {
    return productName;
  }

  @Override
  public ZonedDateTime getProductCreationDate() {
    return productCreationDate;
  }


  @Override
  public Double getProductSoldPrice() {
    return null;
  }

  @Override
  public String getProductStatusCode() {
    return productStatusCode;
  }

  @Override
  public String getProductCategoryCode() {
    return productCategoryCode;
  }

}
