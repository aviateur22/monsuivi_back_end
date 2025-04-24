package com.ctoutweb.monsuivi.infra.adapter.common;

import com.ctoutweb.monsuivi.infra.repository.entity.SellerEntity;
import org.springframework.stereotype.Component;

@Component
public class AdapterCommonMapper {

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
}
