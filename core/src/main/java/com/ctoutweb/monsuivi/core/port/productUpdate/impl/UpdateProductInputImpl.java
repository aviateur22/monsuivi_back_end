package com.ctoutweb.monsuivi.core.port.productUpdate.impl;

import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateInput;

import java.time.LocalDate;

public record UpdateProductInputImpl(
        Long productId,
        Long sellerId,
        double productPurchasePrice,
        double productSoldPrice,
        LocalDate productSoldDate
) implements IProductUpdateInput {
  @Override
  public Long getProductId() {
    return productId;
  }

  @Override
  public Long getSellerId() {
    return sellerId;
  }

  @Override
  public double getProductPurchasePrice() {
    return productPurchasePrice;
  }

  @Override
  public double getProductSoldPrice() {
    return productSoldPrice;
  }

  @Override
  public LocalDate getProductSoldDate() {
    return productSoldDate;
  }
}
