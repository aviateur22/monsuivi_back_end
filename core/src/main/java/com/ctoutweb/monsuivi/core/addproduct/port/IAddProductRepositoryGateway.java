package com.ctoutweb.monsuivi.core.addproduct.port;

import com.ctoutweb.monsuivi.core.entity.product.IProduct;

public interface IAddProductRepositoryGateway {
  public boolean isSellerFind(long sellerIdent);

  public IAddProductOutput saveProductInformations(IProduct productToSave, long sellerIdent, String responseMessage);
}
