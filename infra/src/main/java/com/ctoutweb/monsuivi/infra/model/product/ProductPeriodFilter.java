package com.ctoutweb.monsuivi.infra.model.product;

public enum ProductPeriodFilter {
  ONE_WEEK((short) 1, (short) 7),
  ONE_MONTH((short) 2,(short) 31),
  TWO_MONTH((short) 3,(short) 60),
  THREE_MONTH((short) 4,(short) 90);


  /**
   * Période en nombre de jours
   */
  private short periodInDay;

  /**
   * Code de la période
   */
  private short periodCode;
  private ProductPeriodFilter(short periodCode, short periodInDay) {
    this.periodInDay = periodInDay;
    this.periodCode = periodCode;
  }
  public short getPeriodInday() {
    return this.periodInDay;
  }
  public short getPeriodCode() {
    return this.periodCode;
  }

  public static ProductPeriodFilter findPeriod(Short periodShortFormat) {
    for(var period: ProductPeriodFilter.values()) {
      if(period.getPeriodCode() == periodShortFormat) {
        return period;
      }
    }
    return null;
  }
}
