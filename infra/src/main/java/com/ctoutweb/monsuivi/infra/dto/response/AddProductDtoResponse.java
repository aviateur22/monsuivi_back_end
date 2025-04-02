package com.ctoutweb.monsuivi.infra.dto.response;

public record AddProductDtoResponse(long productId, String responseMessage) implements IResponseMessage{
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
