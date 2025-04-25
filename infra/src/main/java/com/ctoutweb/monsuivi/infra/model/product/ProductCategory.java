package com.ctoutweb.monsuivi.infra.model.product;

import com.ctoutweb.monsuivi.infra.exception.BadRequestException;

import java.util.Arrays;

public enum ProductCategory {
  BOOK("bk", "livre"),
  GAME("ga", "jeu"),
  CLOTHE("cl", "vétement");
  private String code;

  private String label;

  ProductCategory(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public String getCode() {
    return this.code;
  }

  public String getLabel() {
    return this.label;
  }

  public static ProductCategory getProductCategory (String productCodeFrontEnd) {
    return Arrays.stream(ProductCategory.values())
            .filter(cat->cat.getCode().equalsIgnoreCase(productCodeFrontEnd))
            .findFirst()
            .orElseThrow(()-> new BadRequestException("La catégory du produit n'existe pas"));

  }
}
