package com.ctoutweb.monsuivi.infra.dto.response;

import com.ctoutweb.monsuivi.infra.model.message.response.IResponseMessage;

public record DesactivateProductDtoResponse(
        long productId,
        long sellerId,
        boolean isProductActif,
        String responseMessage
) implements IResponseMessage {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
