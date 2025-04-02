package com.ctoutweb.monsuivi.core.entity.product.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProduct;
import com.ctoutweb.monsuivi.core.entity.product.ProductState;
import com.ctoutweb.monsuivi.core.entity.product.ProductCategory;

import java.io.File;
import java.time.ZonedDateTime;

public record ProductImpl() implements IProduct {
  @Override
  public File getProductImage() {
    return null;
  }

  @Override
  public Double getProductPurchasePrice() {
    return null;
  }

  @Override
  public String getProductName() {
    return null;
  }

  @Override
  public ZonedDateTime getProductCreationDate() {
    return null;
  }

  @Override
  public Double getProductDesiredSoldPrice() {
    return null;
  }

  @Override
  public Double getProductSoldPrice() {
    return null;
  }

  @Override
  public ProductState getProductState() {
    return null;
  }

  @Override
  public ProductCategory getProductCategory() {
    return null;
  }
}
