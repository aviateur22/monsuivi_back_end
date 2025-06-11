package com.ctoutweb.monsuivi.infra.adapter.common;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import org.springframework.stereotype.Component;

@Component
public class AdapterCommonMapper {
  private final CoreFactory coreFactory;

  public AdapterCommonMapper(CoreFactory coreFactory) {
    this.coreFactory = coreFactory;
  }

  /**
   * Map vers un SellerEntity
   * @param sellerId
   * @return
   */
  public SellerEntity getSellerEntityFromSellerId(long sellerId) {
    SellerEntity seller = new SellerEntity();
    seller.setId(sellerId);
    return  seller;
  }

  /**
   * Récupération du path de la premiere image d'un produit
   * @param product ProductEntity
   * @return String - path de l'image
   */
  public String getFirstProductImagePath(ProductEntity product) {
    String imagePath = "";
    if(product.getImages() != null && !product.getImages().isEmpty())
      imagePath = product.getImages().get(0).getImagePath();

    return imagePath;
  }

  /**
   * Map un product Entity vers un IProductDetail
   * @param product ProductEntity
   * @return IProductDetail
   */
  public IProductDetail mapToProductDetail(ProductEntity product) {
    return coreFactory.getProductDetailImpl(
            product.getId(),
            getFirstProductImagePath(product),
            product.getProductPurchasePrice(),
            product.getProductName(),
            product.getProductBuyAt(),
            product.getProductSoldAt(),
            product.getProductSoldPrice() == null ? 0 : product.getProductSoldPrice(),
            product.getProductStatus(),
            product.getProductCategory()
    );
  }
}
