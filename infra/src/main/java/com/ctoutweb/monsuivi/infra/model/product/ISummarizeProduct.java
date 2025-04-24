package com.ctoutweb.monsuivi.infra.model.product;

/**
 * Produit
 */
public interface ISummarizeProduct {
  String getTitle();
  IProductCategory getProductCategory();
  IProductStatus getProductStatus();
  String getImageToShow();
}
