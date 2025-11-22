package com.ctoutweb.monsuivi.infra.dto.response;

import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;

public record AddProductDtoResponse(long productId, String responseMessage) implements IResponseMessage {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
