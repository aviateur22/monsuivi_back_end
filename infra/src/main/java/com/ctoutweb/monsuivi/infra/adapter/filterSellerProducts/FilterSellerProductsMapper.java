package com.ctoutweb.monsuivi.infra.adapter.filterSellerProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsInput;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.dto.FilterSellerProductsDto;
import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;
import com.ctoutweb.monsuivi.infra.model.product.ProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.ProductPeriodFilter;
import com.ctoutweb.monsuivi.infra.model.product.ProductStatus;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterSellerProductsMapper {
  private final CoreFactory coreFactory;
  private final AdapterCommonMapper commonMapper;
  private final InfraFactory infraFactory;

  public FilterSellerProductsMapper(CoreFactory coreFactory, AdapterCommonMapper commonMapper, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.commonMapper = commonMapper;
    this.infraFactory = infraFactory;
  }

  public IFilterSellerProductsInput mapToInput(long sellerId, FilterSellerProductsDto dto) {
    return this.coreFactory.getFilterSellerProductsInputImpl(
            sellerId,
            dto.filterByName(),
            dto.filterByCategoryCode(),
            mapDtoPeriodToPeriodInDay(dto.filterByRegisterPeriod()),
            dto.areSoldProductVisible()
    );

  }
  public Short mapDtoPeriodToPeriodInDay(Short dtoPeriod) {
   if(dtoPeriod == null)
     return null;

   var requestPeriodFilter = ProductPeriodFilter.findPeriod(dtoPeriod);

   if(requestPeriodFilter == null)
     return null;

   return requestPeriodFilter.getPeriodInday();


  }

  public IProductDetail mapProductEntityToProductDetail(ProductEntity product) {
    return coreFactory.getProductDetailImpl(
            product.getId(),
            commonMapper.getFirstProductImagePath(product),
            product.getProductPurchasePrice(),
            product.getProductName(),
            product.getProductBuyAt(),
            product.getProductSoldAt(),
            product.getProductSoldPrice(),
            product.getProductStatus(),
            product.getProductCategory()
    );
  }

  public GetSellerProductsDtoReponse mapToResponseDto(IFilterSellerProductsOutput output) {

    List<ISummarizeProduct> products = output.getSellerFilterProducts().stream()
            .map(coreProduct->mapToSummarizeProduct(coreProduct))
            .toList();

    return new GetSellerProductsDtoReponse(output.getResponseMessage(), products, products.size());

  }
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
