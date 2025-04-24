package com.ctoutweb.monsuivi.infra.model.product.impl;

import com.ctoutweb.monsuivi.infra.model.product.IProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.IProductStatus;
import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;
import com.ctoutweb.monsuivi.infra.model.product.ProductCategory;

public record SummarizeProductImpl(
        long id,
        String title,
        IProductCategory productCategory,
        IProductStatus productStatus,
        String imageToShow) implements ISummarizeProduct {
  @Override
  public long getId() {
    return id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public IProductCategory getProductCategory() {
    return productCategory;
  }

  @Override
  public IProductStatus getProductStatus() {
    return productStatus;
  }

  @Override
  public String getImageToShow() {
    return imageToShow;
  }
}
