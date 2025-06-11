package com.ctoutweb.monsuivi.core.rule;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;

import java.util.List;

public interface IFilterSellerProductsRules {
  /**
   * Initialisation
   * @return IFilterSellerProductsRules
   */
  IFilterSellerProductsRules initialiseRule();
  IFilterSellerProductsRules getSellerProducts(long sellerId);
  IFilterSellerProductsRules filterByProductName(String productNameInput);
  IFilterSellerProductsRules filterByProductCategory(String productCategoryInput);
  IFilterSellerProductsRules filterByRegisterPeriod(Short periodInDays);

  /**
   * Renvoie la liste filtrée au format IProductSummarize
   * @return List<IProductSummarize>
   */
  List<IProductSummarize> filteredProducts();


}
