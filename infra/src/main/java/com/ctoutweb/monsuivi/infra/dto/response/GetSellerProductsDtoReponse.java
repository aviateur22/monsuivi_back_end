package com.ctoutweb.monsuivi.infra.dto.response;

import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;

import java.util.List;

public record GetSellerProductsDtoReponse(
        String responseMessage,
        List<ISummarizeProduct> sellerProducts) {

}
