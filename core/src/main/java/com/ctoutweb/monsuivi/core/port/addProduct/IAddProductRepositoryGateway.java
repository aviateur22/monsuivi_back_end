package com.ctoutweb.monsuivi.core.port.addProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProductToAdd;

public interface IAddProductRepositoryGateway {
  public boolean isSellerFind(long sellerIdent);

  public IAddProductOutput saveProductInformations(IProductToAdd productToSave, long sellerIdent, String responseMessage);
}
