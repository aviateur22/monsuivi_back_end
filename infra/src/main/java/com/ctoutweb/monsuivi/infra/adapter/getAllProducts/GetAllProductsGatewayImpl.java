package com.ctoutweb.monsuivi.infra.adapter.getAllProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsGateway;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.adapter.getAllProducts.mapper.GetAllProductMapper;
import com.ctoutweb.monsuivi.infra.repository.IImageRepository;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsGatewayImpl implements IGetAllProductsGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final ISellerRepository sellerRepository;
  private final IProductRepository productRepository;
  private final AdapterCommonMapper commonMapper;
  private final CoreFactory coreFactory;
  private final GetAllProductMapper getAllProductMapper;

  public GetAllProductsGatewayImpl(
          ISellerRepository sellerRepository,
          IProductRepository productRepository,
          AdapterCommonMapper mapper,
          CoreFactory coreFactory,
          GetAllProductMapper getAllProductMapper) {
    this.sellerRepository = sellerRepository;
    this.productRepository = productRepository;
    this.coreFactory = coreFactory;
    this.commonMapper = mapper;
    this.getAllProductMapper = getAllProductMapper;
  }

  @Override
  public boolean isSellerFind(long sellerIdent) {
    LOGGER.debug(()->String.format("[GetAllProductsGatewayImpl] - [isSellerFind]. SellerId: %s", sellerIdent));
    return sellerRepository.findById(sellerIdent).orElse(null) != null;
  }

  @Override
  public IGetAllProductsOutput getAllProducts(long sellerIdent, String responsMessage) {
    LOGGER.debug(()->String.format("[GetAllProductsGatewayImpl]-[getAllProducts]. SellerId: %s", sellerIdent));
    List<IProductSummarize> products = productRepository.findBySellerOrderByCreatedAtDesc(commonMapper.getSellerEntityFromSellerId(sellerIdent))
            .stream()
            .map(productEntity-> getAllProductMapper.mapProductEntityToProductSummarize(productEntity))
            .toList();

    return coreFactory.getGetAllProductsOutputImpl(responsMessage, products);
  }
}
