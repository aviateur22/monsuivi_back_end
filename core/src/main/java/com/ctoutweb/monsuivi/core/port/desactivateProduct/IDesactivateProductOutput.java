package com.ctoutweb.monsuivi.core.port.desactivateProduct;

import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

public interface IDesactivateProductOutput extends IResponsMessage {
  public long getSellerId();
  public long getProductId();
  public boolean getProductIsActif();
}
