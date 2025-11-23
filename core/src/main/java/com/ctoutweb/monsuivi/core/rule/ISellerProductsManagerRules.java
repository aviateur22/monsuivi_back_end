package com.ctoutweb.monsuivi.core.rule;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;

import java.util.List;

public interface ISellerProductsManagerRules {
  /**
   * Initialisation
   * @return IFilterSellerProductsRules
   */
  ISellerProductsManagerRules initialiseRule();
  ISellerProductsManagerRules getSellerProducts(long sellerId);
  ISellerProductsManagerRules getDesactivateProducts(long sellerId);
  ISellerProductsManagerRules filterByProductName(String productNameInput);
  ISellerProductsManagerRules filterByProductCategory(String productCategoryInput);
  ISellerProductsManagerRules filterByRegisterPeriod(Short periodInDays);
  ISellerProductsManagerRules filterByAreSoldProductVisible(boolean areSoldProductVisible);
  /**
   * Renvoie la liste filtrée au format IProductSummarize
   * @return List<IProductSummarize>
   */
  List<IProductSummarize> getProducts();


}
