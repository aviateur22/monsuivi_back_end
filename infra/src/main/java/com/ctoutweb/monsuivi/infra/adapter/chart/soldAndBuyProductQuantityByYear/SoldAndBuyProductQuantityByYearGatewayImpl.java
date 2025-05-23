package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductQuantityByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SoldAndBuyProductQuantityByYearGatewayImpl implements ISoldAndBuyProductQuantityByYearGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final SoldAndBuyProductQuantityByYearMapper soldAndBuyProductQuantityByYearMapper;

  public SoldAndBuyProductQuantityByYearGatewayImpl(
          IProductRepository productRepository,
          SoldAndBuyProductQuantityByYearMapper soldAndBuyProductQuantityByYearMapper) {
    this.productRepository = productRepository;
    this.soldAndBuyProductQuantityByYearMapper = soldAndBuyProductQuantityByYearMapper;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductQuantityByYear> getSoldAndBuyProductQuantityByYearList(long sellerId, short requestedYear) {
    LOGGER.debug(()->String.format("[SoldAndBuyProductQuantityByYearGatewayImpl] - [getSoldAndBuyProductQuantityByYearList] sellerId : %s, year: %S", sellerId, requestedYear));
    var data =  productRepository.getSoldAndBuyProductQuantityByYearList(sellerId, requestedYear);
    var result = soldAndBuyProductQuantityByYearMapper.mapToSoldAndBuyProductQuantity(data);
    LOGGER.debug(()->String.format("[SoldAndBuyProductQuantityByYearGatewayImpl] - [getSoldAndBuyProductQuantityByYearList] resultat : %s", result));
    return  result;
  }
}
