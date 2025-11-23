package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.core.port.getDesactivateSellerProducts.GetDesactivateSellerProductsInput;
import com.ctoutweb.monsuivi.core.usecase.*;
import com.ctoutweb.monsuivi.infra.adapter.desactivateProduct.mapper.DesactivateProductMapper;
import com.ctoutweb.monsuivi.infra.adapter.filterSellerProducts.FilterSellerProductsMapper;
import com.ctoutweb.monsuivi.infra.adapter.getAllProducts.mapper.GetAllProductMapper;
import com.ctoutweb.monsuivi.infra.adapter.getProductDetail.mapper.ProductDetailMapper;
import com.ctoutweb.monsuivi.infra.adapter.updateProduct.ProductUpdateMapper;
import com.ctoutweb.monsuivi.infra.dto.FilterSellerProductsDto;
import com.ctoutweb.monsuivi.infra.dto.UpdateProductDto;
import com.ctoutweb.monsuivi.infra.dto.response.*;
import com.ctoutweb.monsuivi.infra.service.IFileService;
import com.ctoutweb.monsuivi.infra.service.IProductService;
import com.ctoutweb.monsuivi.infra.util.DateUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IFileService fileService;
  private final DesactivateProductMapper updateProductActivationMapper;
  private final GetAllProductMapper mapper;
  private final ProductDetailMapper productDetailMapper;
  private final ProductUpdateMapper productUpdateMapper;
  private final FilterSellerProductsMapper filterSellerProductsMapper;
  private final GetAllSellerProductsUseCase getAllSellerProductsUseCase;
  private final UpdateProductActivationUseCase desactivateProductUseCase;
  private final GetProductDetailUseCase productDetailUseCase;
  private final ProductUpdateUseCase productUpdateUseCase;
  private final FilterSellerProductsUseCase filterSellerProductsUseCase;
  private final GetSellerDesactivateProductsUseCase getSellerDesactivateProductsUseCase;

  public ProductServiceImpl(
          GetAllSellerProductsUseCase getAllSellerProductsUseCase,
          UpdateProductActivationUseCase desactivateProductUseCase,
          GetAllProductMapper mapper,
          DesactivateProductMapper desactivateProductMapper,
          IFileService fileService,
          ProductDetailMapper productDetailMapper,
          ProductUpdateMapper productUpdateMapper,
          FilterSellerProductsMapper filterSellerProductsMapper,
          GetProductDetailUseCase productDetailUseCase,
          ProductUpdateUseCase updateProductUseCase,
          FilterSellerProductsUseCase filterSellerProductsUseCase, GetSellerDesactivateProductsUseCase getSellerDesactivateProductsUseCase) {
    this.fileService = fileService;
    this.getAllSellerProductsUseCase = getAllSellerProductsUseCase;
    this.desactivateProductUseCase = desactivateProductUseCase;
    this.productUpdateMapper = productUpdateMapper;
    this.filterSellerProductsMapper = filterSellerProductsMapper;
    this.productDetailUseCase = productDetailUseCase;
    this.mapper = mapper;
    this.updateProductActivationMapper = desactivateProductMapper;
    this.productDetailMapper = productDetailMapper;
    this.productUpdateUseCase = updateProductUseCase;
    this.filterSellerProductsUseCase = filterSellerProductsUseCase;
    this.getSellerDesactivateProductsUseCase = getSellerDesactivateProductsUseCase;
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
  public UpdateProductActivationDtoResponse desactivateProduct(long productId, long sellerId) {
    LOGGER.debug(()->"[ProductServiceImpl]-[desactivateProduct]");

    final boolean isProductActif = false;

    UpdateProductActivationUseCase.Input input =   new UpdateProductActivationUseCase.Input(
            updateProductActivationMapper.mapToUseCaseInput(productId, sellerId, isProductActif));

    UpdateProductActivationUseCase.Output output = desactivateProductUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[desactivateProduct] - output du useCase:", output.getOutputBoundary()));
    return updateProductActivationMapper.mapToDtoResponse(output.getOutputBoundary());
  }

  @Override
  public GetProductDetailResponseDto getDetailProduct(long productId, long sellerId) {
    LOGGER.debug(()->"[ProductServiceImpl]-[getDetailProduct]");

    GetProductDetailUseCase.Input input = new GetProductDetailUseCase.Input(
            productDetailMapper.mapToCoreInput(sellerId, productId)
    );
    LOGGER.debug(()->String.format("[ProductServiceImpl]-[getDetailProduct] - input: %s", input));
    GetProductDetailUseCase.Output output = productDetailUseCase.execute(input);
    LOGGER.debug(()->String.format("[ProductServiceImpl]-[getDetailProduct] - output: %s", output));

    return productDetailMapper.mapToResponseDto(output.getOutputBoundary());
  }

  @Override
  public ProductUpdateResponseDto updateProduct(UpdateProductDto dto) {
    LOGGER.debug(()->"[ProductServiceImpl]-[updateProduct]");

    ProductUpdateUseCase.Input input = new ProductUpdateUseCase.Input(
            productUpdateMapper.mapToInput(
                    dto.productId(),
                    dto.sellerId(),
                    dto.productPurchasePrice(),
                    dto.productSoldPrice(),
                    DateUtil.convertStringDdMmYyToLocalDate(dto.productBuyDay()),
                    DateUtil.convertStringDdMmYyToLocalDate(dto.productSoldDay()),
                    dto.productStatus()
            )
    );
    LOGGER.debug(()->String.format("[ProductServiceImpl]-[updateProduct] - input: %s", input));

    ProductUpdateUseCase.Output output = productUpdateUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[updateProduct] - output: %s", output));

    return productUpdateMapper.mapToResponseDto(output.getOutputBoundary());
  }

  @Override
  public GetSellerProductsDtoReponse filterSellerProducts(long sellerId, FilterSellerProductsDto dto) {
    LOGGER.debug(()->"[ProductServiceImpl]-[filterSellerProducts]");
    FilterSellerProductsUseCase.Input input = new FilterSellerProductsUseCase.Input(
            filterSellerProductsMapper.mapToInput(sellerId, dto)
    );

    var output = filterSellerProductsUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[filterSellerProducts] - Output: %s", output));

    return  filterSellerProductsMapper.mapToResponseDto(output.getOutputBoundary());
  }

  @Override
  public GetSellerProductsDtoReponse getDesactivateProducts(long sellerId) {
    LOGGER.debug(()->"[ProductServiceImpl]-[getDesactivateProducts]");
    GetSellerDesactivateProductsUseCase.Input input = new GetSellerDesactivateProductsUseCase.Input(new GetDesactivateSellerProductsInput() {
      @Override
      public long getSellerId() {
        return sellerId;
      }
    }
    );

    var output = getSellerDesactivateProductsUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[getDesactivateProducts] - Output: %s", output));

    return mapper.mapToResponseDto(output.getOutputBoundary());
  }

  @Override
  public UpdateProductActivationDtoResponse activateProduct(long productId, long sellerId) {
    LOGGER.debug(()->"[ProductServiceImpl]-[activateProduct]");

    final boolean isProductActif = true;

    UpdateProductActivationUseCase.Input input =   new UpdateProductActivationUseCase.Input(
            updateProductActivationMapper.mapToUseCaseInput(productId, sellerId, isProductActif));

    UpdateProductActivationUseCase.Output output = desactivateProductUseCase.execute(input);

    LOGGER.debug(()->String.format("[ProductServiceImpl]-[activateProduct] - output du useCase:", output.getOutputBoundary()));
    return updateProductActivationMapper.mapToDtoResponse(output.getOutputBoundary());
  }
}
