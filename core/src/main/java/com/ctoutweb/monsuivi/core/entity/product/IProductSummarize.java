package com.ctoutweb.monsuivi.core.entity.product;

import java.io.File;
import java.time.ZonedDateTime;

public interface IProductSummarize {
  File getProductImage();
  String getProductName();
  ZonedDateTime getProductCreationDate();
  ProductState getProductState();
  ProductCategory getProductCategory();
}
