package com.ctoutweb.monsuivi.infra.adapter.desactivateProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProductDesactivate;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductGateway;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.adapter.common.AdapterCommonMapper;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import com.ctoutweb.monsuivi.infra.repository.entity.ProductEntity;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DesactivateProductGatewayImpl implements IDesactivateProductGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final InfraFactory infraFactory;
  private final CoreFactory coreFactory;
  private final AdapterCommonMapper commonMapper;

  /**
   * Produit à desactiver
   */
  private ProductEntity productToDesactivate = null;

  public DesactivateProductGatewayImpl(IProductRepository productRepository, InfraFactory infraFactory, CoreFactory coreFactory, AdapterCommonMapper adapterCommonMapper) {
    this.productRepository = productRepository;
    this.infraFactory = infraFactory;
    this.coreFactory = coreFactory;
    this.commonMapper = adapterCommonMapper;
  }

  @Override
  public boolean canSellerDesactivateProduct(long productId, long sellerId) {
    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [canSellerDesactivateProduct]. produit à desactiver: %s", this.productToDesactivate));

    this.productToDesactivate = productRepository.findByIdAndSeller(productId, commonMapper.getSellerEntityFromSellerId(sellerId))
            .orElse(null);

    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [canSellerDesactivateProduct]. produit à desactiver aprés recherche en base: %s", this.productToDesactivate));

    return this.productToDesactivate != null;
  }
  @Override
  public IProductDesactivate desactivateProduct(long productId) {
    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [desactivateProduct]. produit.istActif avant sauvegarde de: %s", this.productToDesactivate.getIsActif()));
    this.productToDesactivate.setIsActif(false);
    var product = this.productRepository.save(productToDesactivate);
    LOGGER.debug(()->String.format("[DesactivateProductGatewayImpl] - [desactivateProduct]. produit.istActif apres sauvegarde: %s", product.getIsActif()));
    return coreFactory.getProductDesactivate(product.getSeller().getId(), productId, product.getIsActif());
  }
}
