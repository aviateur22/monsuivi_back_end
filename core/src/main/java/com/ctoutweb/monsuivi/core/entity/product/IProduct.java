package com.ctoutweb.monsuivi.core.entity.product;

import java.io.File;
import java.time.ZonedDateTime;

public interface IProduct {
  File getProductImage();
  Double getProductPurchasePrice();
  String getProductName();
  ZonedDateTime getProductCreationDate();
  Double getProductDesiredSoldPrice();
  Double getProductSoldPrice();
  ProductState getProductState();
  ProductCategory getProductCategory();
}
