package com.ctoutweb.monsuivi.infra.dto.response;

import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;

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
