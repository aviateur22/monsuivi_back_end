package com.ctoutweb.monsuivi.infra.adapter.updateProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateGateway;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateInput;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdateGatewayImpl implements IProductUpdateGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final AdapterCommonMapper commonMapper;
  private ProductEntity productDetail = null;

  public ProductUpdateGatewayImpl(IProductRepository productRepository, AdapterCommonMapper commonMapper) {
    this.productRepository = productRepository;
    this.commonMapper = commonMapper;
  }

  @Override
  public boolean canSellerUpdateProduct(long productId, long sellerId) {
    LOGGER.debug(()->String.format("[ProductUpdateGatewayImpl] - [canSellerUpdateProduct]. productId: %s, sellerId: %s", productId, sellerId));

    this.productDetail = productRepository.findByIdAndSellerAndIsActifTrue(productId, commonMapper.getSellerEntityFromSellerId(sellerId))
            .orElse(null);

    return productDetail != null;
  }

  @Override
  public IProductDetail updateProduct(IProductUpdateInput product) {
    this.productDetail.setProductPurchasePrice(product.getProductPurchasePrice());
    this.productDetail.setProductSoldPrice(product.getProductSoldPrice());
    this.productDetail.setProductBuyAt(product.getProductBuyDay());
    this.productDetail.setProductSoldAt(product.getProductSoldDay());
    this.productDetail.setProductStatus(product.getProductStatus());

    ProductEntity updatedProduct = productRepository.save(productDetail);

    return commonMapper.mapToProductDetail(updatedProduct);
  }
}
