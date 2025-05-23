package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldAndBuyProductPriceByYearGatewayImpl implements ISoldAndBuyProductPriceByYearGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final SoldAndBuyProductPriceByYearMapper soldAndBuyProductPriceByYearMapper;

  public SoldAndBuyProductPriceByYearGatewayImpl(
          IProductRepository productRepository,
          SoldAndBuyProductPriceByYearMapper soldAndBuyProductPriceByYearMapper) {
    this.productRepository = productRepository;
    this.soldAndBuyProductPriceByYearMapper = soldAndBuyProductPriceByYearMapper;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductPriceByYear> getSoldAndBuyProductPriceByYearList(long sellerId, short year) {
    LOGGER.debug(()->String.format("[SoldAndBuyProductPriceByYearGatewayImpl] - [getSoldAndBuyProductPriceByYearList] sellerId : %s, year: %S", sellerId, year));
    var data =  productRepository.getSoldAndBuyProductPriceByYearList(sellerId, year);
    var result = soldAndBuyProductPriceByYearMapper.mapToSoldAndBuyProductPriceByYearList(data);
    LOGGER.debug(()->String.format("[SoldAndBuyProductPriceByYearGatewayImpl] - [getSoldAndBuyProductPriceByYearList] resultat : %s", result));
    return  result;
  }
}
