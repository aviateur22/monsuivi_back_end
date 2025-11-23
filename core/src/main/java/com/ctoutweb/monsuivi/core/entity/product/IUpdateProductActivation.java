package com.ctoutweb.monsuivi.core.entity.product;

/**
 * Données d'un produit aprés l'avoir désactiver
 */
public interface IUpdateProductActivation {
  public long getSellerId();
  public long getProductId();
  public boolean getIsProductActif();
}
