package com.ctoutweb.monsuivi.infra.adapter.desactivateProduct;

import com.ctoutweb.monsuivi.core.entity.product.IUpdateProductActivation;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IUpdateProductActivationGateway;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DesactivateProductGatewayImpl implements IUpdateProductActivationGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final CoreFactory coreFactory;
  private final AdapterCommonMapper commonMapper;

  /**
   * Produit à desactiver
   */
  private ProductEntity productActivationToUpdate = null;

  public DesactivateProductGatewayImpl(
          IProductRepository productRepository,
          CoreFactory coreFactory,
          AdapterCommonMapper adapterCommonMapper) {
    this.productRepository = productRepository;
    this.coreFactory = coreFactory;
    this.commonMapper = adapterCommonMapper;
  }

  @Override
  public boolean canSellerDesactivateProduct(long productId, long sellerId) {
    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [canSellerDesactivateProduct]. produit à desactiver: %s", this.productActivationToUpdate));

    this.productActivationToUpdate = productRepository.findByIdAndSeller(productId, commonMapper.getSellerEntityFromSellerId(sellerId))
            .orElse(null);

    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [canSellerDesactivateProduct]. produit à desactiver aprés recherche en base: %s", this.productActivationToUpdate));

    return this.productActivationToUpdate != null;
  }
  @Override
  public IUpdateProductActivation updateProductActivation(long productId, boolean isProductActivate) {
    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [desactivateProduct]. produit.istActif avant sauvegarde de: %s", this.productActivationToUpdate.getIsActif()));
    this.productActivationToUpdate.setIsActif(isProductActivate);
    var product = this.productRepository.save(productActivationToUpdate);
    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [desactivateProduct]. produit.istActif apres sauvegarde: %s", product.getIsActif()));
    return coreFactory.getProductDesactivate(product.getSeller().getId(), productId, product.getIsActif());
  }
}
