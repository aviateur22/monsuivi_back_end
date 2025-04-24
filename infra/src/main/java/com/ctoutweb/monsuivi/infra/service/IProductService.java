package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;

public interface IProductService {
  GetSellerProductsDtoReponse getAllSellerProducts(long sellerId);
}
