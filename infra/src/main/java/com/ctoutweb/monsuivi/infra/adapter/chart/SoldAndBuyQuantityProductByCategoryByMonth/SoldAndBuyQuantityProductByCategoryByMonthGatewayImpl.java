package com.ctoutweb.monsuivi.infra.adapter.chart.SoldAndBuyQuantityProductByCategoryByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyQuantityProductByCategoryByMonthGateway;

import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldAndBuyQuantityProductByCategoryByMonthGatewayImpl implements ISoldAndBuyQuantityProductByCategoryByMonthGateway {
  private static final Logger LOGGER = LogManager.getLogger();

  private final IProductRepository productRepository;
  private final SoldAndBuyQuantityProductByCategoryByMonthMapper SoldAndBuyQuantityProductByCategoryByMonthMapper;

  public SoldAndBuyQuantityProductByCategoryByMonthGatewayImpl(
          IProductRepository productRepository,
          SoldAndBuyQuantityProductByCategoryByMonthMapper SoldAndBuyQuantityProductByCategoryByMonthMapper) {
    this.productRepository = productRepository;
    this.SoldAndBuyQuantityProductByCategoryByMonthMapper = SoldAndBuyQuantityProductByCategoryByMonthMapper;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductQuantityByCategoryAndMonth> getSoldAndBuyProductQuantityByCategoryAndMonthList(long sellerId, String requestedMonth) {
    LOGGER.debug(()->String.format("[SoldAndBuyQuantityProductByCategoryByMonthGatewayImpl] - [getSellerSoldAndBuyQuantityProduct] sellerId : %s, month: %S", sellerId, requestedMonth));
    var data =  productRepository.getSoldAndBuyProductQuantityiesByCategoryAndMonthList(sellerId, requestedMonth);
    var result = SoldAndBuyQuantityProductByCategoryByMonthMapper.mapToSoldAndBuyProductQuantityByCategoryAndMonth(data);
    LOGGER.debug(()->String.format("[SoldAndBuyQuantityProductByCategoryByMonthGatewayImpl] - [getSellerSoldAndBuyQuantityProduct] resultat : %s", result));
    return  result;
  }
}
