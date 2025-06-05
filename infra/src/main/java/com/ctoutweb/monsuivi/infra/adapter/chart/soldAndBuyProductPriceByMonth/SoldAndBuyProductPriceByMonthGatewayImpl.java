package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldAndBuyProductPriceByMonthGatewayImpl implements ISoldAndBuyProductPriceByMonthGateway<ISoldAndBuyProductPriceByMonth> {
    private final IProductRepository productRepository;

  public SoldAndBuyProductPriceByMonthGatewayImpl(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductPriceByMonth> getProductDataByMonth(long sellerId, String requestedMonth) {
    return productRepository.getSoldAndBuyProductPriceByMonth(sellerId, requestedMonth);
  }
}
