package com.ctoutweb.monsuivi.core.port.productUpdate;

import java.time.LocalDate;

public interface IProductUpdateInput {
  Long getProductId();
  Long getSellerId();
  double getProductPurchasePrice();
  double getProductSoldPrice();
  LocalDate getProductSoldDate();
}
