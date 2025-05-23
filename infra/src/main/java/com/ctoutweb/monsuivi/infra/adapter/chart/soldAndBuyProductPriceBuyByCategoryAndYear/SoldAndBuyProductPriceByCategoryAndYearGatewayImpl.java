package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceBuyByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceByCategoryAndYearGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SoldAndBuyProductPriceByCategoryAndYearGatewayImpl implements ISoldAndBuyProductPriceByCategoryAndYearGateway {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IProductRepository productRepository;
  private final SoldAndBuyProductPriceByCategoryAndYearMapper soldAndBuyProductPriceByCategoryAndYearMapper;

  public SoldAndBuyProductPriceByCategoryAndYearGatewayImpl(IProductRepository productRepository, SoldAndBuyProductPriceByCategoryAndYearMapper soldAndBuyProductPriceByCategoryAndYearMapper) {
    this.productRepository = productRepository;
    this.soldAndBuyProductPriceByCategoryAndYearMapper = soldAndBuyProductPriceByCategoryAndYearMapper;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductPriceByCategoryAndYear> getSoldAndBuyProductPriceByCategoryAndYearList(long sellerId, short year) {
    LOGGER.debug(()->String.format("[SoldAndBuyProductPriceByCategoryAndYearGatewayImpl] - [getSoldAndBuyProductPriceByCategoryAndYearList] sellerId : %s, year: %S", sellerId, year));
    var data =  productRepository.getSoldAndBuyProductPricesByCategoryAndYearList(sellerId,year);
    var result = soldAndBuyProductPriceByCategoryAndYearMapper.mapToSoldAndBuyProductPriceByCategoryAndYear(data);
    LOGGER.debug(()->String.format("[SoldAndBuyProductPriceByCategoryAndYearGatewayImpl] - [getSoldAndBuyProductPriceByCategoryAndYearList] resulta :%s", result));
    return result;
  }
}
