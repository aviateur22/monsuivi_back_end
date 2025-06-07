package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyPriceProductByCategoryAndMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldAndBuyProductPriceByCategoryAndMonthGatewayImpl implements ISoldAndBuyProductPriceByCategoryAndMonthGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final SoldAndBuyProductPriceByCategoryAndMonthMapper soldAndBuyProductPriceByCategoryAndMonthMapper;

  public SoldAndBuyProductPriceByCategoryAndMonthGatewayImpl(IProductRepository productRepository, SoldAndBuyProductPriceByCategoryAndMonthMapper soldAndBuyProductPriceByCategoryAndMonthMapper) {
    this.productRepository = productRepository;
    this.soldAndBuyProductPriceByCategoryAndMonthMapper = soldAndBuyProductPriceByCategoryAndMonthMapper;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductPriceByCategoryAndMonth> getSoldAndBuyProductPriceByCategoryAndMonthList(long sellerId, String requestedMonth) {
    LOGGER.debug(()->String.format("[SoldAndBuyProductPriceByCategoryAndMonthGatewayImpl] - [getSoldAndBuyProductPriceByCategoryAndMonthList] sellerId : %s, month: %S", sellerId, requestedMonth));
    var data =  productRepository.getSoldAndBuyProductsPricesByCategoryAndMonthList(sellerId, requestedMonth);
    var result = soldAndBuyProductPriceByCategoryAndMonthMapper.mapToSoldAndBuyProductPriceByCategoryAndMonth(data);
    LOGGER.debug(()->String.format("[SoldAndBuyProductPriceByCategoryAndMonthGatewayImpl] - [getSoldAndBuyProductPriceByCategoryAndMonthList] resultat : %s", result));
    return  result;
  }
}
