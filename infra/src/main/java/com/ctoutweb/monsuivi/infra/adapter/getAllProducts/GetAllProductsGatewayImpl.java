package com.ctoutweb.monsuivi.infra.adapter.getAllProducts;

import com.ctoutweb.monsuivi.core.port.displayAllProducts.IGetAllProductsGateway;
import com.ctoutweb.monsuivi.core.port.displayAllProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.infra.adapter.common.Mapper;
import com.ctoutweb.monsuivi.infra.repository.IImageRepository;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllProductsGatewayImpl implements IGetAllProductsGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final ISellerRepository sellerRepository;
  private final IProductRepository productRepository;
  private final IImageRepository imageRepository;
  private final Mapper mapper;

  public GetAllProductsGatewayImpl(
          ISellerRepository sellerRepository,
          IProductRepository productRepository,
          IImageRepository imageRepository, Mapper mapper) {
    this.sellerRepository = sellerRepository;
    this.productRepository = productRepository;
    this.imageRepository = imageRepository;
    this.mapper = mapper;
  }

  @Override
  public boolean isSellerFind(long sellerIdent) {
    LOGGER.debug(()->String.format("[GetAllProductsGatewayImpl] - [isSellerFind]. SellerId: %s", sellerIdent));
    return sellerRepository.findById(sellerIdent).orElse(null) != null;
  }

  @Override
  public IGetAllProductsOutput getAllProducts(long sellerIdent, String responsMessage) {
    LOGGER.debug(()->String.format("[GetAllProductsGatewayImpl]-[getAllProducts]. SellerId: %s", sellerIdent));
    List<ProductEntity> products = productRepository.findBySeller(mapper.getSellerEntityFromSellerId(sellerIdent));
    return null;
  }
}
