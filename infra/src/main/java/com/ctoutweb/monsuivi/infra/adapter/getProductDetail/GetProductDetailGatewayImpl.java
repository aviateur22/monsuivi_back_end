package com.ctoutweb.monsuivi.infra.adapter.getProductDetail;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailGateway;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.adapter.getProductDetail.mapper.ProductDetailMapper;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GetProductDetailGatewayImpl implements IGetProductDetailGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final AdapterCommonMapper commonMapper;
  private final ProductDetailMapper productDetailMapper;
  private ProductEntity productDetail = null;

  public GetProductDetailGatewayImpl(
          IProductRepository productRepository,
          AdapterCommonMapper commonMapper,
          ProductDetailMapper productDetailMapper) {
    this.productRepository = productRepository;
    this.commonMapper = commonMapper;
    this.productDetailMapper = productDetailMapper;
  }

  @Override
  public boolean canSellerGetProductDetail(long productId, long sellerId) {
    LOGGER.debug(()->String.format("[GetProductDetailGatewayImpl] - [canSellerGetProductDetail]. productId: %s, sellerId: %s", productId, sellerId));

    this.productDetail = productRepository.findByIdAndSellerAndIsActifTrue(productId, commonMapper.getSellerEntityFromSellerId(sellerId))
            .orElse(null);

    return productDetail != null;
  }

  @Override
  public IProductDetail getProductDetail() {
    LOGGER.debug(()->String.format("[GetProductDetailGatewayImpl] - [getProductDetail]. productId: %s", this.productDetail));

    return commonMapper.mapToProductDetail(this.productDetail);
  }
}
