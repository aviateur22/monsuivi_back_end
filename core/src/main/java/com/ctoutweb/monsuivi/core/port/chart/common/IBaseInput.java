package com.ctoutweb.monsuivi.core.port.chart.common;

public interface IBaseInput {
  /**
   * Id du vendeur
   * @return Long
   */
  long getSellerId();

  /**
   * Année
   * @return short
   */
  short getYear();
}
