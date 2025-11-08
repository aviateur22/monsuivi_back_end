package com.ctoutweb.monsuivi.infra.adapter.filterSellerProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.port.common.ISellerProductsManagerGateway;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterSellerProductsGatewayImpl implements ISellerProductsManagerGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final ISellerRepository sellerRepository;
 private final AdapterCommonMapper commonMapper;
 private final FilterSellerProductsMapper filterSellerProductsMapper;

  public FilterSellerProductsGatewayImpl(
          IProductRepository productRepository,
          ISellerRepository sellerRepository,
          AdapterCommonMapper commonMapper,
          FilterSellerProductsMapper filterSellerProductsMapper) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
    this.commonMapper = commonMapper;
    this.filterSellerProductsMapper = filterSellerProductsMapper;
  }

  @Override
  public boolean isSellerFind(long sellerId) {
    LOGGER.debug(()->String.format("[FilterSellerProductsGatewayImpl] - isSellerFind. SellerId: %s", sellerId));
    return sellerRepository.findById(sellerId).orElse(null) != null;
  }

  @Override
  public List<IProductDetail> getAllSellerProducts(long sellerId) {
    LOGGER.debug(()->"[FilterSellerProductsGatewayImpl] - [getAllSellerProducts]");
    List<ProductEntity> products = productRepository.findByIsActifTrueAndSellerOrderByProductBuyAtDesc(commonMapper.getSellerEntityFromSellerId(sellerId));

    // Renvoie la liste mapper vers IProductDetail
    return products.stream()
            .map(filterSellerProductsMapper::mapProductEntityToProductDetail)
            .collect(Collectors.toList());
  }
}
