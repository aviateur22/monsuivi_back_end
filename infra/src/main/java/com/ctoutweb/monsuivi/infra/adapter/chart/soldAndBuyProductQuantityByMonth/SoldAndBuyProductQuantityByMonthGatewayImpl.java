package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductQuantityByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldAndBuyProductQuantityByMonthGatewayImpl implements ISoldAndBuyProductQuantityByMonthGateway<ISoldAndBuyProductQuantityByMonth> {
  private final IProductRepository productRepository;
  public SoldAndBuyProductQuantityByMonthGatewayImpl(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductQuantityByMonth> getProductDataByMonth(long sellerId, String requestedMonth) {
    return productRepository.getSoldAndBuyProductQuantityByMonth(sellerId, requestedMonth);
  }
}
