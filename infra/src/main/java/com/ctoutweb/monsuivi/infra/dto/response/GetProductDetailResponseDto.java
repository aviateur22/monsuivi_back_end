package com.ctoutweb.monsuivi.infra.dto.response;

import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;

public record GetProductDetailResponseDto(
        long productId,
        double productPurchasePrice,
        double productSoldPrice,
        String productName,
        String photoImagePath,
        String productBuyAt,
        String productSoldAt,
        String productStatus,
        long sellerId,
        String responseMessage

) implements IResponseMessage {

  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
