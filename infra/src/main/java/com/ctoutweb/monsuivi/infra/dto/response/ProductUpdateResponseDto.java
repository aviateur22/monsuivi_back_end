package com.ctoutweb.monsuivi.infra.dto.response;

import java.time.LocalDate;

public record ProductUpdateResponseDto(
        long productId,
        double productPurchasePrice,
        double productSoldPrice,
        LocalDate productBuyDay,
        LocalDate productSoldDay,
        String productStatus,
        String responseMessage

) implements IResponseMessage {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
