package com.ctoutweb.monsuivi.core.rule.impl;

import com.ctoutweb.monsuivi.core.rule.IFilterSellerProductsRules;
import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.mapper.CoreMapper;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsGateway;
import com.ctoutweb.monsuivi.core.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterSellerProductsRuleImpl implements IFilterSellerProductsRules {
  private final IFilterSellerProductsGateway filterSellerProductsGateway;
  private List<IProductDetail> sellerProducts = new ArrayList<>();
  private CoreMapper coreMapper = InstanceLoader.getMapper();

  public FilterSellerProductsRuleImpl(IFilterSellerProductsGateway filterSellerProductsGateway) {
    this.filterSellerProductsGateway = filterSellerProductsGateway;
  }

  @Override
  public IFilterSellerProductsRules initialiseRule() {
    this.sellerProducts = new ArrayList<>();
    return this;
  }

  @Override
  public IFilterSellerProductsRules getSellerProducts(long sellerId) {

    if(!filterSellerProductsGateway.isSellerFind(sellerId))
      throw new CoreException("Utilisateur inconnu");

    this.sellerProducts = filterSellerProductsGateway.getAllSellerProducts(sellerId);

    return this;
  }

  @Override
  public IFilterSellerProductsRules filterByProductName(String productNameInput) {
    if(productNameInput == null)
      return this;

    // Filtre les produits dont le nom commence par "productNameInput"
    this.sellerProducts = sellerProducts
            .stream()
            .filter(productDetail -> productDetail.getProductName().toLowerCase().startsWith(productNameInput.toLowerCase()))
            .collect(Collectors.toList());

    return this;
  }

  @Override
  public IFilterSellerProductsRules filterByProductCategory(String productCategoryInput) {
    if(productCategoryInput == null)
      return this;

    // Filtre les produits dont le nom commence par "productNameInput"
    this.sellerProducts = sellerProducts
            .stream()
            .filter(productDetail -> productDetail.getProductCategoryCode().equalsIgnoreCase(productCategoryInput))
            .collect(Collectors.toList());

    return this;
  }

  @Override
  public IFilterSellerProductsRules filterByRegisterPeriod(Short periodInDays) {
    if(periodInDays == null)
      return this;

    // Date de récupération max des produit
    LocalDate limitProductBuyDate = DateUtil.getLocalDateFromAPeriodInDay(periodInDays);

    // Filtre les produits dont le nom commence par "productNameInput"
    this.sellerProducts = sellerProducts
            .stream()
            .filter(productDetail -> productDetail.getProductBuyAt().isAfter(limitProductBuyDate))
            .collect(Collectors.toList());

    return this;
  }

  @Override
  public List<IProductSummarize> filteredProducts() {
    return this.sellerProducts.stream()
            .map(productDetail -> coreMapper.map(productDetail, coreMapper.mapFunction()))
            .collect(Collectors.toList());
  }
}
