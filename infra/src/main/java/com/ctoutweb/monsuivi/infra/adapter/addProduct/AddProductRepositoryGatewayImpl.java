package com.ctoutweb.monsuivi.infra.adapter.addProduct;

import com.ctoutweb.monsuivi.core.addproduct.port.IAddProductOutput;
import com.ctoutweb.monsuivi.core.addproduct.port.IAddProductRepositoryGateway;
import com.ctoutweb.monsuivi.core.entity.product.IProduct;
import com.ctoutweb.monsuivi.infra.adapter.addProduct.mapper.AddProductMapper;
import com.ctoutweb.monsuivi.infra.repository.IImageRepository;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.ISellerRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ImageEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import com.ctoutweb.monsuivi.infra.service.IFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Adapter pour AddProductGateway
 */
@Component
public class AddProductRepositoryGatewayImpl implements IAddProductRepositoryGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final ISellerRepository sellerRepository;
  private final IProductRepository productRepository;
  private final IImageRepository imageRepository;
  private final IFileService fileService;
  private final AddProductMapper addProductMapper;

  public AddProductRepositoryGatewayImpl(
          ISellerRepository sellerRepository,
          IProductRepository productRepository,
          IImageRepository imageRepository, IFileService fileService,
          AddProductMapper addProductMapper) {
    this.sellerRepository = sellerRepository;
    this.productRepository = productRepository;
    this.imageRepository = imageRepository;
    this.fileService = fileService;
    this.addProductMapper = addProductMapper;
  }

  @Override
  public boolean isSellerFind(long sellerIdent) {
    LOGGER.debug(()->String.format("[AddProductRepositoryGatewayImpl] - isSellerFind. SellerId: %s", sellerIdent));
    return sellerRepository.findById(sellerIdent).orElse(null) != null;
  }

  @Override
  public IAddProductOutput saveProductInformations(IProduct productToSave, long sellerIdent, String responseMessage) {
    LOGGER.debug(()->String.format("[AddProductRepositoryGatewayImpl] - saveProductInformations. SellerId: %s, productToSave: %s", sellerIdent, productToSave));

   // Sauvegarde les données du produit
    ProductEntity product = addProductMapper.mapToProductEntity(productToSave, sellerIdent);
    product = productRepository.save(product);

    // Sauvagarde de l'image
    this.saveProductImage(productToSave.getProductImage(), product);

    return addProductMapper.maptToProductOutput(product.getId(), responseMessage);
  }

  /**
   * Sauvegarde de l'image du produit
   * @param productImage
   * @param productSaved
   */
  public void saveProductImage(File productImage, ProductEntity productSaved) {
    try {
      LOGGER.debug(()->String.format("[AddProductRepositoryGatewayImpl] - saveProductImage. productImage: %s, ProductEntity: %s", productImage, productSaved));
      // Sauvegarde de l'image
      String imagePath = fileService.uploadFile(addProductMapper.mapToFileToImageToSave(productImage));

      // sauvgarde du path d'accés de l'image
      ImageEntity image = addProductMapper.mapToImageEntity(imagePath, productSaved);
      imageRepository.save(image);

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
