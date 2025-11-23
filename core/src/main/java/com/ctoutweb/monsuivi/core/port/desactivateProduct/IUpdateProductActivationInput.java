package com.ctoutweb.monsuivi.core.port.desactivateProduct;

public interface IUpdateProductActivationInput {
  public long getProductId();
  public long getSellerId();
  public boolean getIsProductActivate();
}
