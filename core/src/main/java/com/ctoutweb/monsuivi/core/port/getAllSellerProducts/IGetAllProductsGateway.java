package com.ctoutweb.monsuivi.core.port.getAllSellerProducts;

public interface IGetAllProductsGateway {
  public boolean isSellerFind(long sellerIdent);
  public IGetAllProductsOutput getAllProducts(long sellerIdent, String responsMessage);
}
