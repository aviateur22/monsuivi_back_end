package com.ctoutweb.monsuivi.core.entity.product;

/**
 * Données d'un produit aprés l'avoir désactiver
 */
public interface IProductDesactivate {
  public long getSellerId();
  public long getProductId();
  public boolean getIsProductActif();
}
