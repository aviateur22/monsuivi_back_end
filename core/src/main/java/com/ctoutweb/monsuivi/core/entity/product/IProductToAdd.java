package com.ctoutweb.monsuivi.core.entity.product;

import java.io.File;
import java.time.ZonedDateTime;

public interface IProductToAdd {
  File getProductImage();
  Double getProductPurchasePrice();
  String getProductName();
  ZonedDateTime getProductCreationDate();
  Double getProductSoldPrice();
  String getProductStatusCode();
  String getProductCategoryCode();
}
