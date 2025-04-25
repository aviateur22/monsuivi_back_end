package com.ctoutweb.monsuivi.core.factory;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductSummarizeImpl;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.impl.GetAllSellerProductsImpl;

import java.util.List;

@CoreService
public class CoreFactory {
  public IProductSummarize getProductSummarizeImpl(
          long id,
          String imagePath,
          String productName,
          String statusCode,
          String categorycode){
    return new ProductSummarizeImpl(id, imagePath, productName, statusCode, categorycode);
  }

  public IGetAllProductsOutput getGetAllProductsOutputImpl(
          String responseMessage,
          List<IProductSummarize> products) {
    return new GetAllSellerProductsImpl(responseMessage, products);
  }
}
