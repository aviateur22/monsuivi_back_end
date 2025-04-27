package com.ctoutweb.monsuivi.infra.adapter.addProduct.mapper;

import com.ctoutweb.monsuivi.core.entity.product.impl.ProductToAddImpl;
import com.ctoutweb.monsuivi.core.port.addProduct.IAddProductInput;
import com.ctoutweb.monsuivi.core.port.addProduct.IAddProductOutput;
import com.ctoutweb.monsuivi.core.entity.product.IProductToAdd;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.dto.AddProductDto;
import com.ctoutweb.monsuivi.infra.dto.response.AddProductDtoResponse;
import com.ctoutweb.monsuivi.infra.exception.BadRequestException;
import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;
import com.ctoutweb.monsuivi.infra.model.product.ProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.ProductStatus;
import com.ctoutweb.monsuivi.infra.repository.entity.ImageEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import com.ctoutweb.monsuivi.infra.util.DateUtil;
import com.ctoutweb.monsuivi.infra.util.TextUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.ctoutweb.monsuivi.infra.constant.Constant.IS_PRODUCT_ACTIF_AT_CREATION;

@Component
public class AddProductMapper {
  private static final Logger LOGGER = LogManager.getLogger();
  @Value("${zone.id}")
  private String zoneId;
  private final InfraFactory infraFactory;
  private final AdapterCommonMapper mapper;
  public AddProductMapper(InfraFactory infraFactory, AdapterCommonMapper mapper) {
    this.infraFactory = infraFactory;
    this.mapper = mapper;
  }

  /**
   * Renvoie un produtcEntity pour sauvegarde
   * @param productCoreInformation
   * @param sellerId
   * @return
   */
  public ProductEntity mapToProductEntity(IProductToAdd productCoreInformation, long sellerId) {
    ProductEntity product = new ProductEntity();
    product.setProductName(productCoreInformation.getProductName());
    product.setProductPurchasePrice(productCoreInformation.getProductPurchasePrice());
    product.setCreatedAt(DateUtil.localDateTimeToZonedDateTime(ZoneId.of(zoneId), LocalDateTime.now()));
    product.setProductBuyAt(LocalDate.now());
    product.setIsActif(IS_PRODUCT_ACTIF_AT_CREATION);
    product.setProductDesiredSoldPrice(productCoreInformation.getProductDesiredSoldPrice());
    product.setSeller(mapper.getSellerEntityFromSellerId(sellerId));
    product.setProductCategory(productCoreInformation.getProductCategoryCode());
    product.setProductStatus(productCoreInformation.getProductStatusCode());
    return product;
  }

  /**
   * Map une données File vers IImageToSave
   * @param imageCoreInformation File
   * @return IImageToSave
   * @throws FileNotFoundException
   */
  public IImageToSave mapToFileToImageToSave(File imageCoreInformation) throws FileNotFoundException {
    IImageToSave imageToSave = infraFactory.getImageToSaveImpl();
    imageToSave.setFileSize(imageCoreInformation.length());
    imageToSave.setRandomFileName(TextUtil.getRandomNameUUID());
    imageToSave.setFileExtension(FilenameUtils.getExtension(imageCoreInformation.getName()));
    imageToSave.setFileStream(new FileInputStream(imageCoreInformation));
    return imageToSave;
  }

  /**
   * Map vers ImageEntity
   * @param imagePath String - Path de sauvegarde
   * @param productEntity ProductEntity - Produit enregistré
   * @return ImageEntity
   */
  public ImageEntity mapToImageEntity(String imagePath, ProductEntity productEntity) {
    ImageEntity imageEntity = new ImageEntity();
    imageEntity.setProduct(productEntity);
    imageEntity.setImagePath(imagePath);
    imageEntity.setCreatedAt(DateUtil.localDateTimeToZonedDateTime(ZoneId.of(zoneId), LocalDateTime.now()));
    return imageEntity;
  }

  /**
   * Renvoie un IAddProductOutput
   * @param productId
   * @return IAddProductOutput
   */
  public IAddProductOutput maptToProductOutput(long productId, String responseMessage) {
    return new IAddProductOutput() {
      @Override
      public long getProductId() {
        return productId;
      }

      @Override
      public String getResponseMessage() {
        return responseMessage;
      }
    };
  }

  /**
   * Renvoir un IAddProductInput
   * @param addProductDto AddProductDto
   * @return IAddProductInput
   */
  public IAddProductInput mapToProductInput(AddProductDto addProductDto) {
    return new IAddProductInput() {
      @Override
      public Long getUserId() {
        return addProductDto.sellerId();
      }

      @Override
      public IProductToAdd getProductToSell() {
        return getCoreProduct(addProductDto);
      }
    };
  }

  /**
   * Map vers la réponse Dto du controlle
   * @param productOutput IAddProductOutput
   * @return AddProductDtoResponse
   */
  public AddProductDtoResponse getAddProductResponseDto(IAddProductOutput productOutput) {
    return new AddProductDtoResponse(productOutput.getProductId(), productOutput.getResponseMessage());
  }

  /**
   * Map le AddProductDto vers le core entity IProductToAdd
   * @param productDto AddProductDto
   * @return IProduct
   */
  private IProductToAdd getCoreProduct(AddProductDto productDto) {
    return new ProductToAddImpl(
            getFileFromMultipart(productDto.uploadProductImage()),
                    productDto.productPurchasePrice(),
                    productDto.productName(),
                    productDto.productDesiredSoldPrice(),
                    getProductCategoryCode(productDto.productCategory()),
                    ProductStatus.FOR_SALE.getProductStatusCode(),
            DateUtil.localDateTimeToZonedDateTime(ZoneId.of(zoneId), LocalDateTime.now())
    );
  }

  /**
   * Récupération de la catégory du produit
   * @param productCategory String - Données envoyée depuis le frontEnd
   * @return ProductType
   */
  private String getProductCategoryCode(String productCategory) {
    LOGGER.debug(()->String.format("[AddProductMapper] - getProductCategoryFromDto. productCategory: %s", productCategory));
    return ProductCategory.getProductCategory(productCategory).getCode();
  }

  /**
   * Récupération d'un File à partir d'un MultipartFile
   * @param image MultipartFile - Données a parser en File
   * @return File
   * @throws BadRequestException - Renvoie une erreur sur le triatement de l'image
   */
  private File getFileFromMultipart(MultipartFile image) {
    try {
      String originalFilename = TextUtil.sanitizeDocumentName(image.getOriginalFilename());
      String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
      File file = Files.createTempFile("upload_", fileExtension).toFile();
      try (FileOutputStream fos = new FileOutputStream(file)) {
        fos.write(image.getBytes());
      } catch (IOException e) {
        throw new BadRequestException("Il y a eu une erreur de traitement avec l'image du produit");
      }
      return file;

    } catch (IOException exception) {
     throw new BadRequestException("Il y a eu une erreur de traitement avec l'image du produit");
    }
  }
 }
