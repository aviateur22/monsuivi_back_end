package com.ctoutweb.monsuivi.core.port.addProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProduct;

public interface IAddProductRepositoryGateway {
  public boolean isSellerFind(long sellerIdent);

  public IAddProductOutput saveProductInformations(IProduct productToSave, long sellerIdent, String responseMessage);
}
