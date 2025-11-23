package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.FilterSellerProductsDto;
import com.ctoutweb.monsuivi.infra.dto.UpdateProductDto;
import com.ctoutweb.monsuivi.infra.dto.response.*;
import jakarta.servlet.http.HttpServletResponse;

public interface IProductService {
  GetSellerProductsDtoReponse getAllSellerProducts(long sellerId);
  void streamProductImage(String imagePath, HttpServletResponse httpResponse);
  UpdateProductActivationDtoResponse desactivateProduct(long product, long sellerId);
  GetProductDetailResponseDto getDetailProduct(long product, long sellerId);
  ProductUpdateResponseDto updateProduct(UpdateProductDto dto);
  GetSellerProductsDtoReponse filterSellerProducts(long sellerId, FilterSellerProductsDto dto);
  GetSellerProductsDtoReponse getDesactivateProducts(long sellerId);
  UpdateProductActivationDtoResponse activateProduct(long productId, long sellerId);
}
