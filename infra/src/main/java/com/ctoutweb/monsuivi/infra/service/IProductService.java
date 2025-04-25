package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import jakarta.servlet.http.HttpServletResponse;

public interface IProductService {
  GetSellerProductsDtoReponse getAllSellerProducts(long sellerId);
  void streamProductImage(String imagePath, HttpServletResponse httpResponse);
}
