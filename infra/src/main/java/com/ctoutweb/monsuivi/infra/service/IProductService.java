package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.UpdateProductDto;
import com.ctoutweb.monsuivi.infra.dto.response.DesactivateProductDtoResponse;
import com.ctoutweb.monsuivi.infra.dto.response.GetProductDetailResponseDto;
import com.ctoutweb.monsuivi.infra.dto.response.GetSellerProductsDtoReponse;
import com.ctoutweb.monsuivi.infra.dto.response.ProductUpdateResponseDto;
import jakarta.servlet.http.HttpServletResponse;

public interface IProductService {
  GetSellerProductsDtoReponse getAllSellerProducts(long sellerId);
  void streamProductImage(String imagePath, HttpServletResponse httpResponse);
  DesactivateProductDtoResponse desactivateProduct(long product, long sellerId);
  GetProductDetailResponseDto getDetailProduct(long product, long sellerId);
  ProductUpdateResponseDto updateProduct(UpdateProductDto dto);

}
