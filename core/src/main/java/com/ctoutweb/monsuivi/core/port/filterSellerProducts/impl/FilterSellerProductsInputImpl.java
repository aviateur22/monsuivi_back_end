package com.ctoutweb.monsuivi.core.port.filterSellerProducts.impl;

import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsInput;

public record FilterSellerProductsInputImpl(
        long sellerId,
        String filterProductByNameInput,
        String filterProductByCategoryInput,
        Short filterPeriodInDay

) implements IFilterSellerProductsInput {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public String getFilterProductByNameInput() {
    return filterProductByNameInput;
  }

  @Override
  public String getFilterProductByCategoryInput() {
    return filterProductByCategoryInput;
  }

  @Override
  public Short getFilterPeriodInDayInput() {
    return filterPeriodInDay;
  }
}
