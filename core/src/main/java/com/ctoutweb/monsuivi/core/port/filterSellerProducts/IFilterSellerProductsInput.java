package com.ctoutweb.monsuivi.core.port.filterSellerProducts;

public interface IFilterSellerProductsInput {
  long getSellerId();
  String getFilterProductByNameInput();
  String getFilterProductByCategoryInput();
  String getFilterProductByRegisterDateInput();

}
