package com.ctoutweb.monsuivi.infra;

import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;
import com.ctoutweb.monsuivi.infra.model.image.impl.ImageToSaveImpl;
import com.ctoutweb.monsuivi.infra.model.error.ErrorMessageImpl;
import com.ctoutweb.monsuivi.infra.model.error.IErrorMessage;
import com.ctoutweb.monsuivi.infra.model.product.IProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.IProductStatus;
import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;
import com.ctoutweb.monsuivi.infra.model.product.impl.ProductCategoryImpl;
import com.ctoutweb.monsuivi.infra.model.product.impl.ProductStatusImpl;
import com.ctoutweb.monsuivi.infra.model.product.impl.SummarizeProductImpl;
import org.springframework.stereotype.Component;

@Component
public class InfraFactory {

  public ISummarizeProduct getProductSummarizeImpl(
          long id,
          String title,
          IProductCategory category,
          IProductStatus productStatus,
          String imageToshow) {
    return new SummarizeProductImpl(id,title, category, productStatus, imageToshow);
  }

  public IProductCategory getProductCategoryImpl(String code, String category) {
    return new ProductCategoryImpl(code, category);
  }

  public IProductStatus getProductStatusImpl(String code, String status) {
    return new ProductStatusImpl(code, status);
  }
  public IImageToSave getImageToSaveImpl() {
    return new ImageToSaveImpl();
  }

  public IErrorMessage getErrorMessageImpl(String errorMessage) {
    return new ErrorMessageImpl(errorMessage);
  }
}
