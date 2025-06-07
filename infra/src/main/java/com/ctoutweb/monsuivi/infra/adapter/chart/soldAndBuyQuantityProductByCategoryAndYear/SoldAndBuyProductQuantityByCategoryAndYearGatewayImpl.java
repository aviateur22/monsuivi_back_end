package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyQuantityProductByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearGateway;
import com.ctoutweb.monsuivi.infra.repository.IProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldAndBuyProductQuantityByCategoryAndYearGatewayImpl implements ISoldAndBuyProductQuantityByCategoryAndYearGateway {
  private static final Logger LOGGER = LogManager.getLogger();

  private final IProductRepository productRepository;
  private final SoldAndBuyProductQuantityByCategoryAndYearMapper soldAndBuyProductQuantityByCategoryAndYearMapper;

  public SoldAndBuyProductQuantityByCategoryAndYearGatewayImpl(
          IProductRepository productRepository,
          SoldAndBuyProductQuantityByCategoryAndYearMapper soldAndBuyProductQuantityByCategoryAndYearMapper) {
    this.productRepository = productRepository;
    this.soldAndBuyProductQuantityByCategoryAndYearMapper = soldAndBuyProductQuantityByCategoryAndYearMapper;
  }

  @Override
  public boolean canSellerDisplayChartInformation(long sellerId) {
    return true;
  }

  @Override
  public List<ISoldAndBuyProductQuantityByCategoryAndYear> getSoldAndBuyProductQuantityByCategoryAndYearList(long sellerId, short year) {
    LOGGER.debug(()->String.format("[SoldAndBuyProductQuantityByCategoryAndYearGatewayImpl] - [getSoldAndBuyProductQuantityByCategoryAndYearList] sellerId : %s, year: %S", sellerId, year));
    var data =  productRepository.getSoldAndBuyProductQuantitiesByCategoryAndYearList(sellerId, year);
    var result = soldAndBuyProductQuantityByCategoryAndYearMapper.mapToSoldAndBuyProductQuantityByCategoryAndYear(data);
    LOGGER.debug(()->String.format("[SoldAndBuyProductQuantityByCategoryAndYearGatewayImpl] - [getSoldAndBuyProductQuantityByCategoryAndYearList] resulta :%s", result));
    return result;
  }
}
