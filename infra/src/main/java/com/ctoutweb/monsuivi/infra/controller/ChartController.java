package com.ctoutweb.monsuivi.infra.controller;

import com.ctoutweb.monsuivi.infra.dto.response.chart.*;
import com.ctoutweb.monsuivi.infra.service.IChartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/charts")
public class ChartController {

  private final IChartService chartService;

  public ChartController(IChartService chartService) {
    this.chartService = chartService;
  }

  @GetMapping("/product-quantity-by-category-and-month/seller/{sellerId}/month/{month}/year/{year}")
  public ResponseEntity<SoldAndBuyQuantityProductByCategoryAndMonthResponseDto> getProductQuantityByCategoryAndMonth(
          @PathVariable Long sellerId, @PathVariable Short month, @PathVariable Short year) {

    SoldAndBuyQuantityProductByCategoryAndMonthResponseDto dto = chartService.getSoldAndBuyProductQuantityByCategoryAndMonth(sellerId, month, year);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/product-price-by-category-and-month/seller/{sellerId}/month/{month}/year/{year}")
  public ResponseEntity<SoldAndBuyProductPriceByCategoryAndMonthResponseDto> getProductPriceByCategoryAndMonth(
          @PathVariable Long sellerId, @PathVariable Short month, @PathVariable Short year
  ){
    SoldAndBuyProductPriceByCategoryAndMonthResponseDto dto = chartService.getSoldAndBuyProductPriceByCategoryAndMonth(sellerId, month, year);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/product-price-by-category-and-year/seller/{sellerId}/year/{year}")
  public ResponseEntity<SoldAndBuyProductPriceByCategoryAndYearResponseDto> getProductPriceByCategoryAndYear(
          @PathVariable Long sellerId, @PathVariable Short year
  ){
    SoldAndBuyProductPriceByCategoryAndYearResponseDto dto = chartService.getSoldAndBuyProductPriceByCategoryAndYear(sellerId, year);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/product-quantity-by-category-and-year/seller/{sellerId}/year/{year}")
  public ResponseEntity<SoldAndBuyProductQuantityByCategoryAndYearResponseDto> getProductQuantityByCategoryAndYear(
          @PathVariable Long sellerId, @PathVariable Short year
  ){
    SoldAndBuyProductQuantityByCategoryAndYearResponseDto dto = chartService.getSoldAndBuyProductQuantityByCategoryAndYear(sellerId, year);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/product-quantity-by-year/seller/{sellerId}/year/{year}")
  public ResponseEntity<SoldAndBuyQuantityProductByYearResponseDto> getProductQuantityByYear(
          @PathVariable Long sellerId, @PathVariable Short year
  ){
    SoldAndBuyQuantityProductByYearResponseDto dto = chartService.getSoldAndBuyQuantityProductByYear(sellerId, year);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/product-price-by-year/seller/{sellerId}/year/{year}")
  public ResponseEntity<SoldAndBuyProductPriceByYearResponseDto> getProductPriceByYear(
          @PathVariable Long sellerId, @PathVariable Short year
  ){
    SoldAndBuyProductPriceByYearResponseDto dto = chartService.getSoldAndBuyProductPriceByYear(sellerId, year);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }
}
