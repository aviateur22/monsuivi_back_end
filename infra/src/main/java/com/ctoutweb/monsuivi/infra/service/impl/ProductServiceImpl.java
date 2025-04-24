package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.core.usecase.GetAllSellerProductsUseCase;
import com.ctoutweb.monsuivi.infra.adapter.getAllProducts.mapper.GetAllProductMapper;
import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import com.ctoutweb.monsuivi.infra.service.IProductService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
  private static final Logger LOGGER = LogManager.getLogger();
  private final GetAllSellerProductsUseCase getAllSellerProductsUseCase;
  private final GetAllProductMapper mapper;

  public ProductServiceImpl(
          GetAllSellerProductsUseCase getAllSellerProductsUseCase,
          GetAllProductMapper mapper) {
    this.getAllSellerProductsUseCase = getAllSellerProductsUseCase;
    this.mapper = mapper;
  }

  @Transactional
  @Override
  public GetSellerProductsDtoReponse getAllSellerProducts(long sellerId) {
    LOGGER.debug(()->String.format("[ProductServiceImpl]-[getAllSellerProducts] - id seller:", sellerId));

    GetAllSellerProductsUseCase.Input input = new GetAllSellerProductsUseCase.Input(mapper.getAllProductsInput(sellerId));

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[getAllSellerProducts] - Input du useCase:", input));

    GetAllSellerProductsUseCase.Output output = getAllSellerProductsUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[getAllSellerProducts] - output du useCase:", output));

    return mapper.mapToResponseDto(output.getOutputBoundary());
  }
}
