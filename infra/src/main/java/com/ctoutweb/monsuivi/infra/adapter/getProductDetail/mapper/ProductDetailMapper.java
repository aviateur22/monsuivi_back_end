package com.ctoutweb.monsuivi.infra.adapter.getProductDetail.mapper;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailInput;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.dto.response.GetProductDetailResponseDto;
import com.ctoutweb.monsuivi.infra.model.product.ProductCategory;
import com.ctoutweb.monsuivi.infra.util.DateUtil;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {
  private final InfraFactory infraFactory;
  private final CoreFactory coreFactory;
  private final AdapterCommonMapper commonMapper;

  public ProductDetailMapper(InfraFactory infraFactory, CoreFactory coreFactory, AdapterCommonMapper commonMapper) {
      this.infraFactory = infraFactory;
      this.coreFactory = coreFactory;
    this.commonMapper = commonMapper;
  }

  /**
   * Map vers le core Input
   * @param sellerId long
   * @param productId long
   * @return IProductDetailInput
   */
  public IGetProductDetailInput mapToCoreInput(long sellerId, long productId) {
    return coreFactory.getProductDetailInputImpl(productId, sellerId);
  }

  /**
   * Map le core Output vers le DTO
   * @param coreOutput IGetProductDetailOutput
   * @return GetProductDetailResponseDto
   */
  public GetProductDetailResponseDto mapToResponseDto(IGetProductDetailOutput coreOutput) {

    IProductDetail productDetail = coreOutput.getProductDetail();
    ProductCategory productCategory = ProductCategory.getProductCategory(productDetail.getProductCategoryCode());
    long sellerId = coreOutput.getSellerId();
    String responseMessage = coreOutput.getResponseMessage();

    return new GetProductDetailResponseDto(
            productDetail.getProductId(),
            productDetail.getProductPurchasePrice(),
            productDetail.getProductSoldPrice(),
            productDetail.getProductName(),
            productDetail.getProductImagePath(),
            DateUtil.formatToDdMmYy(productDetail.getProductBuyAt()),
            DateUtil.formatToDdMmYy(productDetail.getProductSoldAt()),
            productDetail.getProductStatus(),
            infraFactory.getProductCategoryImpl(productCategory.getCode(), productCategory.getLabel()),
            sellerId,
            responseMessage
    );
  }
}
