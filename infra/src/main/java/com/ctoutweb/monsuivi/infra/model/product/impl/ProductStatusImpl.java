package com.ctoutweb.monsuivi.infra.model.product.impl;

import com.ctoutweb.monsuivi.infra.model.product.IProductStatus;

public record ProductStatusImpl(String code, String statusName) implements IProductStatus {
  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getStatusName() {
    return statusName;
  }

}
