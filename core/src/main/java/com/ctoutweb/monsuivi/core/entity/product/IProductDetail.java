package com.ctoutweb.monsuivi.core.entity.product;

import java.time.LocalDate;
public interface IProductDetail {
  long getProductId();
  String getProductImagePath();
  Double getProductPurchasePrice();
  String getProductName();
  LocalDate getProductCreationDate();
  Double getProductSoldPrice();
}
