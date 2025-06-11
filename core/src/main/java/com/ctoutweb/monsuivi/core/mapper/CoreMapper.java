package com.ctoutweb.monsuivi.core.mapper;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;

import java.util.function.Function;

public class CoreMapper {

  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();

  public<T,U> U map(T data, Function<T, U> mapFunction) {
    return mapFunction.apply(data);
  }

  public Function<IProductDetail, IProductSummarize> mapFunction() {
    return product->coreFactory.getProductSummarizeImpl(
            product.getProductId(),
            product.getProductImagePath(),
            product.getProductName(),
            product.getProductStatus(),
            product.getProductCategoryCode());
  }


}
