package com.ctoutweb.monsuivi.core.port.productUpdate;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

public interface IProductUpdateOuput extends IResponsMessage {
  IProductDetail getProductDetail();
  String getResponseMessage();
}
