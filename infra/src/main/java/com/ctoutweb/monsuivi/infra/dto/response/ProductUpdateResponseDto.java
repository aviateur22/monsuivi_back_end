package com.ctoutweb.monsuivi.infra.dto.response;

public record ProductUpdateResponseDto(
        long productId,
        String responseMessage

) implements IResponseMessage {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
