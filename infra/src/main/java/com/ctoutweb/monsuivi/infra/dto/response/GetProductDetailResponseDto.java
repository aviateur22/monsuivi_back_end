package com.ctoutweb.monsuivi.infra.dto.response;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public record GetProductDetailResponseDto(
        long productId,
        double productPurchasePrice,
        double productSoldPrice,
        String photoName,
        String photoImagePath,
        LocalDate ProductCreationDate,
        long sellerId,
        String responseMessage

) implements IResponseMessage {

  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
