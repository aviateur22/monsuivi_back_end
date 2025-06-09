package com.ctoutweb.monsuivi.core.entity.product;

import java.util.List;

public interface IFilterSellerProductsRule {
  /**
   * Reset des parametres
   * @return IFilterSellerProductsRules
   */
  IFilterSellerProductsRule initialiseRule();
  IFilterSellerProductsRule filterByProductName(String productNameInput);
  IFilterSellerProductsRule filterByProductCategory(String productCategoryInput);
  IFilterSellerProductsRule filterByRegisterPeriod(int periodInDays);

  /**
   * Renvoie la liste filtrée au format IProductSummarize
   * @return List<IProductSummarize>
   */
  List<IProductSummarize> filteredProducts();


}
