package com.ctoutweb.monsuivi.core.port.displayAllProducts;

public interface IGetAllProductsGateway {
  public boolean isSellerFind(long sellerIdent);
  public IGetAllProductsOutput getAllProducts(long sellerIdent, String responsMessage);
}
