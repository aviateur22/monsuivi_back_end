package com.ctoutweb.monsuivi.infra.controller;

import com.ctoutweb.monsuivi.core.usecase.AddProductUseCase;
import com.ctoutweb.monsuivi.infra.adapter.addProduct.AddProductRepositoryGatewayImpl;
import com.ctoutweb.monsuivi.infra.adapter.addProduct.mapper.AddProductMapper;
import com.ctoutweb.monsuivi.infra.annotation.DtoValidator;
import com.ctoutweb.monsuivi.infra.dto.AddProductDto;
import com.ctoutweb.monsuivi.infra.exception.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
  private final DtoValidator dtoValidator;
  private final AddProductRepositoryGatewayImpl addProductRepositoryGateway;
  private final AddProductMapper addProductMapper;
  private final AddProductUseCase addProductUseCase;

  public ProductController(AddProductRepositoryGatewayImpl addProductRepositoryGateway, DtoValidator dtoValidator, AddProductMapper addProductMapper, AddProductUseCase addProductUseCase) {
    this.addProductRepositoryGateway = addProductRepositoryGateway;
    this.dtoValidator = dtoValidator;
    this.addProductMapper = addProductMapper;
    this.addProductUseCase = addProductUseCase;
  }

  @PostMapping("")
  @Transactional
  public ResponseEntity addProduct(@ModelAttribute AddProductDto addProductDto) {
    dtoValidator.validateDto(addProductDto);

    AddProductUseCase.Input input = new AddProductUseCase.Input(addProductMapper.mapToProductInput(addProductDto));
    AddProductUseCase.Output output = addProductUseCase.execute(input);

    return new ResponseEntity(addProductMapper.getAddProductResponseDto(output.getOutputBoundary()), HttpStatus.OK);
  }
}
