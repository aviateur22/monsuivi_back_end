package com.ctoutweb.monsuivi.infra.controller;

import com.ctoutweb.monsuivi.core.usecase.AddProductUseCase;
import com.ctoutweb.monsuivi.infra.adapter.addProduct.mapper.AddProductMapper;
import com.ctoutweb.monsuivi.infra.annotation.DtoValidator;
import com.ctoutweb.monsuivi.infra.dto.AddProductDto;
import com.ctoutweb.monsuivi.infra.dto.DesactivateProductDto;
import com.ctoutweb.monsuivi.infra.dto.UpdateProductDto;
import com.ctoutweb.monsuivi.infra.dto.response.DesactivateProductDtoResponse;
import com.ctoutweb.monsuivi.infra.dto.response.GetProductDetailResponseDto;
import com.ctoutweb.monsuivi.infra.dto.response.ProductUpdateResponseDto;
import com.ctoutweb.monsuivi.infra.service.IProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/products")
public class ProductController {
  private static final Logger LOGGER = LogManager.getLogger();
  private final DtoValidator dtoValidator;
  private final AddProductMapper addProductMapper;
  private final AddProductUseCase addProductUseCase;
  private final IProductService productService;

  public ProductController(
          DtoValidator dtoValidator,
          AddProductMapper addProductMapper,
          AddProductUseCase addProductUseCase,
          IProductService productService) {
    this.dtoValidator = dtoValidator;
    this.addProductMapper = addProductMapper;
    this.addProductUseCase = addProductUseCase;
    this.productService = productService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity addProduct(@ModelAttribute AddProductDto addProductDto) {
    LOGGER.debug(()->String.format("[ProductController]-[addProduct] - Ajout du produit suivant:", addProductDto));

    dtoValidator.validateDto(addProductDto);

    LOGGER.debug(()->"[ProductController]-[addProduct] - Le produit est valide pour l'ajout");
    AddProductUseCase.Input input = new AddProductUseCase.Input(addProductMapper.mapToProductInput(addProductDto));

    LOGGER.debug(()->String.format("[ProductController]-[addProduct] - Input du useCase:", input));

    AddProductUseCase.Output output = addProductUseCase.execute(input);

    return new ResponseEntity(addProductMapper.getAddProductResponseDto(output.getOutputBoundary()), HttpStatus.OK);
  }

  @GetMapping("/seller/{sellerId}")
  public ResponseEntity getSellerProducts(@PathVariable Long sellerId) {
    var sellerProducts = productService.getAllSellerProducts(sellerId);
    return new ResponseEntity(sellerProducts, HttpStatus.OK);
  }

  @GetMapping("/image/{imagePath}")
  public void getImage(@PathVariable String imagePath, HttpServletResponse response) {
    productService.streamProductImage(imagePath, response);
  }

  @PutMapping("/desactivate")
  public ResponseEntity<DesactivateProductDtoResponse> desactivateProduct(@RequestBody DesactivateProductDto desactivateProductDto) {
    LOGGER.debug(()->String.format("[ProductController]-[desactivateProduct] - Deasactivation d'un produit:", desactivateProductDto));
    dtoValidator.validateDto(desactivateProductDto);
    DesactivateProductDtoResponse response = productService.desactivateProduct(
            desactivateProductDto.productId(),
            desactivateProductDto.sellerId());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{productId}/seller-id/{sellerId}")
  public ResponseEntity<GetProductDetailResponseDto> getProductDetail(
            @PathVariable Long productId, @PathVariable Long sellerId) {
      GetProductDetailResponseDto dto = productService.getDetailProduct(productId, sellerId);
      return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PutMapping()
  public ResponseEntity<ProductUpdateResponseDto> updateProduct(
          @RequestBody UpdateProductDto productDto) {
    ProductUpdateResponseDto dto = productService.updateProduct(productDto);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }
}
