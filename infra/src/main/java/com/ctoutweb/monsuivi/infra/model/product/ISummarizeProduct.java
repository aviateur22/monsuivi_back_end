package com.ctoutweb.monsuivi.infra.model.product;

/**
 * Produit
 */
public interface ISummarizeProduct {
  long getId();
  String getTitle();
  IProductCategory getProductCategory();
  IProductStatus getProductStatus();
  String getImageToShow();
}
