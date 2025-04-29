package com.ctoutweb.monsuivi.core.factory;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IProductDesactivate;
import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductDesacativateImpl;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductDetailImpl;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductSummarizeImpl;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductInput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.impl.DesactivateProductInputImpl;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.impl.DesactivateProductOutputImpl;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.impl.GetAllSellerProductsImpl;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailInput;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailOutput;
import com.ctoutweb.monsuivi.core.port.getProductDetail.impl.GetProductDetailInputImpl;
import com.ctoutweb.monsuivi.core.port.getProductDetail.impl.GetProductDetailOutputImpl;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateInput;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateOuput;
import com.ctoutweb.monsuivi.core.port.productUpdate.impl.ProductUpdateOutputImpl;
import com.ctoutweb.monsuivi.core.port.productUpdate.impl.UpdateProductInputImpl;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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

  public IDesactivateProductInput getDesactivateProductInputImpl(long productId, long sellerId) {
    return new DesactivateProductInputImpl(productId, sellerId);
  }
  public IProductDesactivate getProductDesactivate(long sellerId, long productId, boolean productIsActif) {
    return new ProductDesacativateImpl(sellerId, productId, productIsActif);
  }
  public IDesactivateProductOutput getDesactivateProductOutputImpl(
          String responseMessage,
          long sellerId,
          long productId,
          boolean productIsActif){
    return new DesactivateProductOutputImpl(responseMessage, sellerId, productId, productIsActif);
  }

  /**
   * GetDetailProduct -- Factory
   */
  public IGetProductDetailInput getProductDetailInputImpl(long productId, long sellerId) {
    return new GetProductDetailInputImpl(productId, sellerId);
  }

  public IGetProductDetailOutput getProductDetailOutputImpl(
          IProductDetail productDetail,
          long sellerId,
          String responseMessage) {
    return new GetProductDetailOutputImpl(productDetail, sellerId, responseMessage);
  }

  public IProductDetail getProductDetailImpl(
          long productid,
          String imagePath,
          double productPurchasePrice,
          String productName,
          LocalDate productCreationDate,
          double productSoldPrice) {
    return new ProductDetailImpl(productid, imagePath, productPurchasePrice, productName, productCreationDate, productSoldPrice);
  }

  /**
   * ProductUpdate -- Factory
   */
  public IProductUpdateInput getProductUpdateInput(
          Long productId,
          Long sellerId,
          double productPurchasePrice,
          double productSoldPrice,
          LocalDate productSoldDate
  ) {
    return new UpdateProductInputImpl(productId, sellerId, productPurchasePrice, productSoldPrice, productSoldDate);
  }

  public IProductUpdateOuput getProductUpdateOutput(long productId, String responseMessage ){
    return new ProductUpdateOutputImpl(productId, responseMessage);
  }
}
