package com.ctoutweb.monsuivi.infra.model.product;

import com.ctoutweb.monsuivi.infra.exception.BadRequestException;

import java.util.Arrays;

public enum ProductStatus {
  FOR_SALE("fs", "à vendre"),
  SOLD("so", "vendu");

  private String code;

  private String label;

  private ProductStatus(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public String getProductStatusCode() {
    return this.code;
  }

  public String getProductStatusLabel() {
    return this.label;
  }

  /**
   * Renvoie ProductStatus correspondant au Code du status
   * @param statusCode String- code du statut
   * @return ProductStatus
   */
  public static ProductStatus getProductStatus(String statusCode) {
    return Arrays.stream(ProductStatus.values())
            .filter(status->status.getProductStatusCode().equalsIgnoreCase(statusCode))
            .findFirst()
            .orElseThrow(()-> new BadRequestException("Le statut du produit n'existe pas"));
  }
}
