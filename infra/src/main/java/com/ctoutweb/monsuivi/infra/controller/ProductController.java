package com.ctoutweb.monsuivi.infra.controller;

import com.ctoutweb.monsuivi.core.usecase.AddProductUseCase;
import com.ctoutweb.monsuivi.infra.adapter.addProduct.mapper.AddProductMapper;
import com.ctoutweb.monsuivi.infra.annotation.DtoValidator;
import com.ctoutweb.monsuivi.infra.dto.AddProductDto;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/product")
public class ProductController {
  private static final Logger LOGGER = LogManager.getLogger();
  private final DtoValidator dtoValidator;
  private final AddProductMapper addProductMapper;
  private final AddProductUseCase addProductUseCase;

  public ProductController(DtoValidator dtoValidator, AddProductMapper addProductMapper, AddProductUseCase addProductUseCase) {
    this.dtoValidator = dtoValidator;
    this.addProductMapper = addProductMapper;
    this.addProductUseCase = addProductUseCase;
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
}
