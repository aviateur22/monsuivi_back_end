package com.ctoutweb.monsuivi.core.rule.impl;

import com.ctoutweb.monsuivi.core.entity.product.ProductStatus;
import com.ctoutweb.monsuivi.core.port.common.ISellerProductsManagerGateway;
import com.ctoutweb.monsuivi.core.rule.ISellerProductsManagerRules;
import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.mapper.CoreMapper;
import com.ctoutweb.monsuivi.core.util.DateUtil;
import com.ctoutweb.monsuivi.core.util.StringUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerProductsManagerRuleImpl implements ISellerProductsManagerRules {
  private final ISellerProductsManagerGateway filterSellerProductsGateway;
  private List<IProductDetail> sellerProducts = new ArrayList<>();
  private CoreMapper coreMapper = InstanceLoader.getMapper();

  public SellerProductsManagerRuleImpl(ISellerProductsManagerGateway filterSellerProductsGateway) {
    this.filterSellerProductsGateway = filterSellerProductsGateway;
  }

  @Override
  public ISellerProductsManagerRules initialiseRule() {
    this.sellerProducts = new ArrayList<>();
    return this;
  }

  @Override
  public ISellerProductsManagerRules getSellerProducts(long sellerId) {

    if(!filterSellerProductsGateway.isSellerFind(sellerId))
      throw new CoreException("Utilisateur inconnu");

    this.sellerProducts = filterSellerProductsGateway.getAllSellerProducts(sellerId);

    return this;
  }

  @Override
  public ISellerProductsManagerRules filterByProductName(String productNameInput) {
    if(productNameInput == null)
      return this;

    // Filtre les produits dont le nom commence par "productNameInput"
    this.sellerProducts = sellerProducts
            .stream()
            .filter(productDetail -> {
              String nameWithoutAccent = StringUtil.removeAccent(productDetail.getProductName()).toLowerCase();
              String productNameWithoutAccent = StringUtil.removeAccent(productNameInput.toLowerCase()).toLowerCase();
              return nameWithoutAccent.contains(productNameWithoutAccent);
            })
            .collect(Collectors.toList());

    return this;
  }

  @Override
  public ISellerProductsManagerRules filterByProductCategory(String productCategoryInput) {
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
  public ISellerProductsManagerRules filterByRegisterPeriod(Short periodInDays) {
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
  public ISellerProductsManagerRules filterByAreSoldProductVisible(boolean areSoldProductVisible) {
    if(areSoldProductVisible) {
      String statusProductSoldCode = ProductStatus.SOLD.getProductStatusCode();
      this.sellerProducts = sellerProducts
              .stream()
              .filter(productDetail -> statusProductSoldCode.equalsIgnoreCase(productDetail.getProductStatus()))
              .collect(Collectors.toList());
      return this;
    }

    String statusProductSaleCode = ProductStatus.FOR_SALE.getProductStatusCode();
    this.sellerProducts = sellerProducts
            .stream()
            .filter(productDetail -> statusProductSaleCode.equalsIgnoreCase(productDetail.getProductStatus()))
            .collect(Collectors.toList());
    return this;
  }


  @Override
  public List<IProductSummarize> getProducts() {
    return this.sellerProducts.stream()
            .map(productDetail -> coreMapper.map(productDetail, coreMapper.mapFunction()))
            .collect(Collectors.toList());
  }
}
