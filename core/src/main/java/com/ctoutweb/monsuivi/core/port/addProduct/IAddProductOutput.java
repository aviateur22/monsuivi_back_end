package com.ctoutweb.monsuivi.core.port.addProduct;

import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

public interface IAddProductOutput extends IResponsMessage {
  long getProductId();
}
