package com.ctoutweb.monsuivi.infra.adapter.getAllProducts.mapper;

import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsInput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.infra.model.product.IProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;
import com.ctoutweb.monsuivi.infra.model.product.ProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.ProductStatus;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import com.ctoutweb.monsuivi.infra.service.IFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetAllProductMapper {
  private static final Logger LOGGER = LogManager.getLogger();
  private final InfraFactory infraFactory;
  private final CoreFactory coreFactory;
  private final IFileService fileService;
  public GetAllProductMapper(
          InfraFactory factory,
          CoreFactory coreFactory,
          IFileService fileService) {
    this.infraFactory = factory;
    this.coreFactory = coreFactory;
    this.fileService = fileService;
  }

  /**
   * Map un productEntity vers un IProductSummarize
   * @param product ProductEntity - Issue de la base de données
   * @return IProductSummarize
   */
  public IProductSummarize mapProductEntityToProductSummarize(ProductEntity product) {
    String imagePath = "";
    if(!product.getImages().isEmpty())
      imagePath = product.getImages().get(0).getImagePath();

    return coreFactory.getProductSummarizeImpl(
            product.getId(),
            imagePath,
            product.getProductName(),
            product.getProductStatus(),
            product.getProductCategory());
  }
  /**
   * Récupération de input du UseCase GetAllSellerProducts
   * @param sellerId
   * @return
   */
  public IGetAllProductsInput getAllProductsInput(long sellerId) {
    return new IGetAllProductsInput() {
      @Override
      public Long getUserId() {
        return sellerId;
      }
    };
  }
  /**
   * Map la sortie du useCase vers le DTO
   * @param getAllProductsOutput IGetAllProductsOutput
   * @return GetSellerProductsDtoReponse
   */
  public GetSellerProductsDtoReponse mapToResponseDto(IGetAllProductsOutput getAllProductsOutput) {

    List<ISummarizeProduct> products = getAllProductsOutput.getAllProducts().stream()
            .map(coreProduct->mapToSummarizeProduct(coreProduct))
            .toList();

    String response = getAllProductsOutput.getResponseMessage();

    return new GetSellerProductsDtoReponse(response, products);
  }

  /**
   * Renvoie un ISummarizeProduct qui sera intégré au Dto de la réponse
   * @param productSummarize IProductSummarize - Données en provenance du core
   * @return ISummarizeProduct
   */
  public ISummarizeProduct mapToSummarizeProduct(IProductSummarize productSummarize) {

    ProductCategory productCategory = ProductCategory.getProductCategory(productSummarize.getProductCategoryCode());
    ProductStatus productStatus = ProductStatus.getProductStatus(productSummarize.getProductStatusCode());

    return infraFactory.getProductSummarizeImpl(
            productSummarize.getId(),
            productSummarize.getProductName(),
            infraFactory.getProductCategoryImpl(productCategory.getCode(), productCategory.getLabel()),
            infraFactory.getProductStatusImpl(productStatus.getProductStatusCode(), productStatus.getProductStatusLabel()),
            mapPathToImageToShow(productSummarize.getProductImagePath())
    );
  }

  /**
   * Map le path de l'image en base64
   * @param imagePath String - Path pour acceder à l'image
   * @return IImageToShow
   */
  public String mapPathToImageToShow(String imagePath) {
    LOGGER.debug(()->"[GetAllProductMapper]-[mapPathToImageToShow] - Convertion path to base64");
    return fileService.downloadFile(imagePath);
  }
}
