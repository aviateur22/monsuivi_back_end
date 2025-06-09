package com.ctoutweb.monsuivi.core.entity.product.impl.rule;

import com.ctoutweb.monsuivi.core.entity.product.IFilterSellerProductsRule;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;

import java.util.List;

public class FilterSellerProductsRuleImpl implements IFilterSellerProductsRule {
  @Override
  public IFilterSellerProductsRule initialiseRule() {
    return null;
  }

  @Override
  public IFilterSellerProductsRule filterByProductName(String productNameInput) {
    return null;
  }

  @Override
  public IFilterSellerProductsRule filterByProductCategory(String productCategoryInput) {
    return null;
  }

  @Override
  public IFilterSellerProductsRule filterByRegisterPeriod(int periodInDays) {
    return null;
  }

  @Override
  public List<IProductSummarize> filteredProducts() {
    return null;
  }
}
