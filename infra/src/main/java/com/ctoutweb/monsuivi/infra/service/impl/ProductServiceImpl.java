package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.core.usecase.DesactivateProductUseCase;
import com.ctoutweb.monsuivi.core.usecase.GetAllSellerProductsUseCase;
import com.ctoutweb.monsuivi.infra.adapter.desactivateProduct.mapper.DesactivateProductMapper;
import com.ctoutweb.monsuivi.infra.adapter.getAllProducts.mapper.GetAllProductMapper;
import com.ctoutweb.monsuivi.infra.dto.response.DesactivateProductDtoResponse;
import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import com.ctoutweb.monsuivi.infra.service.IFileService;
import com.ctoutweb.monsuivi.infra.service.IProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
  private static final Logger LOGGER = LogManager.getLogger();
  private final GetAllSellerProductsUseCase getAllSellerProductsUseCase;
  private final DesactivateProductUseCase desactivateProductUseCase;
  private final GetAllProductMapper mapper;
  private final DesactivateProductMapper desactivateProductMapper;
  private final IFileService fileService;

  public ProductServiceImpl(
          GetAllSellerProductsUseCase getAllSellerProductsUseCase,
          DesactivateProductUseCase desactivateProductUseCase,
          GetAllProductMapper mapper,
          DesactivateProductMapper desactivateProductMapper,
          IFileService fileService) {
    this.getAllSellerProductsUseCase = getAllSellerProductsUseCase;
    this.desactivateProductUseCase = desactivateProductUseCase;
    this.mapper = mapper;
    this.desactivateProductMapper = desactivateProductMapper;
    this.fileService = fileService;
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

  @Override
  public void streamProductImage(String imagePath, HttpServletResponse httpResponse) {
    fileService.streamFile(imagePath, httpResponse);
  }

  @Override
  @Transactional
  public DesactivateProductDtoResponse desactivateProduct(long productId, long sellerId) {
    LOGGER.debug(()->"[ProductServiceImpl]-[desactivateProduct]");
    DesactivateProductUseCase.Input input =   new DesactivateProductUseCase.Input(
            desactivateProductMapper.mapToUseCaseInput(productId, sellerId));

    DesactivateProductUseCase.Output output = desactivateProductUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[desactivateProduct] - output du useCase:", output.getOutputBoundary()));
    return desactivateProductMapper.mapToDtoResponse(output.getOutputBoundary());
  }
}
