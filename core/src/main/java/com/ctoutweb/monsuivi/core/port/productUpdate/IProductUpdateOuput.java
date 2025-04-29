package com.ctoutweb.monsuivi.core.port.productUpdate;

import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

public interface IProductUpdateOuput extends IResponsMessage {
  long getProductId();
  String getResponseMessage();
}
