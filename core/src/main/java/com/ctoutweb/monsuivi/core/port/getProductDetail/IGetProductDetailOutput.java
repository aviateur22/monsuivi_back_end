package com.ctoutweb.monsuivi.core.port.getProductDetail;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

public interface IGetProductDetailOutput extends IResponsMessage  {
  IProductDetail getProductDetail();
  long getSellerId();
}
