package com.ctoutweb.monsuivi.infra.adapter.getAllProducts.mapper;

import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsInput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;
import com.ctoutweb.monsuivi.infra.model.product.ProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.ProductStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetAllProductMapper {
  private static final Logger LOGGER = LogManager.getLogger();
  private final InfraFactory infraFactory;
  public GetAllProductMapper(
          InfraFactory factory,
          AdapterCommonMapper commonMapper) {
    this.infraFactory = factory;


  }


  /**
   * Récupération de input du UseCase GetAllSellerProducts
   * @param sellerId
   * @param areSoldProductVisible
   * @return
   */
  public IGetAllProductsInput getAllProductsInput(long sellerId, boolean areSoldProductVisible) {
    return new IGetAllProductsInput() {
      @Override
      public Long getUserId() {
        return sellerId;
      }

      @Override
      public boolean getAreSoldProductVisible() {
        return areSoldProductVisible;
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
            productSummarize.getProductImagePath()
    );
  }
}
