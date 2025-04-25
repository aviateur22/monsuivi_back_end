package com.ctoutweb.monsuivi.infra.model.product.impl;

import com.ctoutweb.monsuivi.infra.model.product.IProductCategory;

public record ProductCategoryImpl(String code, String categoryName) implements IProductCategory {
  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getCategoryName() {
    return categoryName;
  }
}
