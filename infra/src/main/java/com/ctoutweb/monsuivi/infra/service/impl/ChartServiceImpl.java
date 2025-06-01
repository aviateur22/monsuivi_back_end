package com.ctoutweb.monsuivi.infra.service.impl;

import com.ctoutweb.monsuivi.core.usecase.chart.*;
import com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyPriceProductByCategoryAndMonth.SoldAndBuyProductPriceByCategoryAndMonthMapper;
import com.ctoutweb.monsuivi.infra.adapter.chart.SoldAndBuyQuantityProductByCategoryByMonth.SoldAndBuyQuantityProductByCategoryByMonthMapper;
import com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceBuyByCategoryAndYear.SoldAndBuyProductPriceByCategoryAndYearMapper;
import com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceByYear.SoldAndBuyProductPriceByYearMapper;
import com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductQuantityByYear.SoldAndBuyProductQuantityByYearMapper;
import com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyQuantityProductByCategoryAndYear.SoldAndBuyProductQuantityByCategoryAndYearMapper;
import com.ctoutweb.monsuivi.infra.dto.response.chart.*;
import com.ctoutweb.monsuivi.infra.service.IChartService;
import org.springframework.stereotype.Service;

@Service
public class ChartServiceImpl implements IChartService {

  private final SoldAndBuyProductQuantityByCategoryMonthUseCase soldAndBuyProductQuantityByCategoryMonthUseCase;
  private final SoldAndBuyProductPriceByCategoryAndMonthUseCase soldAndBuyProductPriceByCategoryAndMonthUseCase;
  private final SoldAndBuyProductPriceByCategoryAndYearUseCase soldAndBuyProductPriceByCategoryAndYearUseCase;
  private final SoldAndBuyProductQuantityByCategoryAndYearUseCase soldAndBuyProductQuantityByCategoryAndYearUseCase;
  private final SoldAndBuyProductQuantityByYearUseCase soldAndBuyProductQuantityByYearUseCase;
  private final SoldAndBuyProductPriceByYearUseCase soldAndBuyProductPriceByYearUseCase;
  private final SoldAndBuyQuantityProductByCategoryByMonthMapper soldAndBuyQuantityProductByCategoryByMonthMapper;
  private final SoldAndBuyProductPriceByCategoryAndMonthMapper soldAndBuyProductPriceByCategoryAndMonthMapper;
  private final SoldAndBuyProductPriceByCategoryAndYearMapper soldAndBuyProductPriceByCategoryAndYearMapper;
  private final SoldAndBuyProductQuantityByCategoryAndYearMapper soldAndBuyProductQuantityByCategoryAndYearMapper;
  private final SoldAndBuyProductQuantityByYearMapper soldAndBuyProductQuantityByYearMapper;
  private final SoldAndBuyProductPriceByYearMapper soldAndBuyProductPriceByYearMapper;



  public ChartServiceImpl(
          SoldAndBuyProductQuantityByCategoryMonthUseCase soldAndBuyProductQuantityByCategoryMonthUseCase,
          SoldAndBuyProductPriceByCategoryAndMonthUseCase soldAndBuyProductPriceByCategoryAndMonthUseCase,
          SoldAndBuyProductPriceByCategoryAndYearUseCase soldAndBuyProductPriceByCategoryAndYearUseCase,
          SoldAndBuyProductQuantityByCategoryAndYearUseCase soldAndBuyProductQuantityByCategoryAndYearUseCase,
          SoldAndBuyProductQuantityByYearUseCase soldAndBuyProductQuantityByYearUseCase,
          SoldAndBuyProductPriceByYearUseCase soldAndBuyProductPriceByYearUseCase,
          SoldAndBuyQuantityProductByCategoryByMonthMapper soldAndBuyQuantityProductByCategoryByMonthMapper,
          SoldAndBuyProductPriceByCategoryAndMonthMapper soldAndBuyProductPriceByCategoryAndMonthMapper,
          SoldAndBuyProductPriceByCategoryAndYearMapper soldAndBuyProductPriceByCategoryAndYearMapper,
          SoldAndBuyProductQuantityByCategoryAndYearMapper soldAndBuyProductQuantityByCategoryAndYearMapper,
          SoldAndBuyProductQuantityByYearMapper soldAndBuyProductQuantityByYearMapper,
          SoldAndBuyProductPriceByYearMapper soldAndBuyProductPriceByYearMapper) {
    this.soldAndBuyProductQuantityByCategoryMonthUseCase = soldAndBuyProductQuantityByCategoryMonthUseCase;
    this.soldAndBuyProductPriceByCategoryAndMonthUseCase = soldAndBuyProductPriceByCategoryAndMonthUseCase;
    this.soldAndBuyProductPriceByCategoryAndYearUseCase = soldAndBuyProductPriceByCategoryAndYearUseCase;
    this.soldAndBuyProductQuantityByCategoryAndYearUseCase = soldAndBuyProductQuantityByCategoryAndYearUseCase;
    this.soldAndBuyProductQuantityByYearUseCase = soldAndBuyProductQuantityByYearUseCase;
    this.soldAndBuyProductPriceByYearUseCase = soldAndBuyProductPriceByYearUseCase;
    this.soldAndBuyQuantityProductByCategoryByMonthMapper = soldAndBuyQuantityProductByCategoryByMonthMapper;
    this.soldAndBuyProductPriceByCategoryAndMonthMapper = soldAndBuyProductPriceByCategoryAndMonthMapper;
    this.soldAndBuyProductPriceByCategoryAndYearMapper = soldAndBuyProductPriceByCategoryAndYearMapper;
    this.soldAndBuyProductQuantityByCategoryAndYearMapper = soldAndBuyProductQuantityByCategoryAndYearMapper;
    this.soldAndBuyProductQuantityByYearMapper = soldAndBuyProductQuantityByYearMapper;
    this.soldAndBuyProductPriceByYearMapper = soldAndBuyProductPriceByYearMapper;
  }

  @Override
  public SoldAndBuyQuantityProductByCategoryAndMonthResponseDto getSoldAndBuyProductQuantityByCategoryAndMonth(long sellerId, short month, short year) {

    SoldAndBuyProductQuantityByCategoryMonthUseCase.Input input = new SoldAndBuyProductQuantityByCategoryMonthUseCase
            .Input(soldAndBuyQuantityProductByCategoryByMonthMapper.getInput(sellerId, month, year));

    var output = this.soldAndBuyProductQuantityByCategoryMonthUseCase.execute(input);
    var datas = output.getOutputBoundary().getDatas();
    var monthRequest = output.getOutputBoundary().getRequestedMonth();
    var yearRequest = output.getOutputBoundary().getRequestedYear();


    return soldAndBuyQuantityProductByCategoryByMonthMapper.mapToDto(datas, monthRequest, String.valueOf(yearRequest));
  }

  @Override
  public SoldAndBuyProductPriceByCategoryAndMonthResponseDto getSoldAndBuyProductPriceByCategoryAndMonth(long sellerId, short month, short year) {
    SoldAndBuyProductPriceByCategoryAndMonthUseCase.Input input = new SoldAndBuyProductPriceByCategoryAndMonthUseCase.Input(
            soldAndBuyProductPriceByCategoryAndMonthMapper.getInput(sellerId, month, year)
    );

    var output = soldAndBuyProductPriceByCategoryAndMonthUseCase.execute(input);

    var datas = output.getOutputBoundary().getDatas();
    var monthRequest = output.getOutputBoundary().getRequestedMonth();
    var yearRequest = output.getOutputBoundary().getRequestedYear();

    return soldAndBuyProductPriceByCategoryAndMonthMapper.mapToDto(datas, monthRequest,String.valueOf(yearRequest));
  }

  @Override
  public SoldAndBuyProductPriceByCategoryAndYearResponseDto getSoldAndBuyProductPriceByCategoryAndYear(long sellerId, short year) {

    SoldAndBuyProductPriceByCategoryAndYearUseCase.Input input = new SoldAndBuyProductPriceByCategoryAndYearUseCase.Input(
            soldAndBuyProductPriceByCategoryAndYearMapper.mapToInput(sellerId, year)
    );

    var output = soldAndBuyProductPriceByCategoryAndYearUseCase.execute(input);

    var datas = output.getOutputBoundary().getDatas();
    var yeaRequest = output.getOutputBoundary().getRequestedYear();

    return soldAndBuyProductPriceByCategoryAndYearMapper.mapToDto(datas, yeaRequest);
  }

  @Override
  public SoldAndBuyProductQuantityByCategoryAndYearResponseDto getSoldAndBuyProductQuantityByCategoryAndYear(long sellerId, short year) {
    SoldAndBuyProductQuantityByCategoryAndYearUseCase.Input input = new SoldAndBuyProductQuantityByCategoryAndYearUseCase.Input(
            soldAndBuyProductQuantityByCategoryAndYearMapper.mapToInput(sellerId, year)
    );

    var output = soldAndBuyProductQuantityByCategoryAndYearUseCase.execute(input);

    var datas = output.getOutputBoundary().getDatas();
    var yeaRequest = output.getOutputBoundary().getRequestedYear();

    return soldAndBuyProductQuantityByCategoryAndYearMapper.mapToDto(datas, yeaRequest);
  }

  @Override
  public SoldAndBuyQuantityProductByYearResponseDto getSoldAndBuyQuantityProductByYear(long sellerId, short year) {
    SoldAndBuyProductQuantityByYearUseCase.Input input = new SoldAndBuyProductQuantityByYearUseCase.Input(
            soldAndBuyProductQuantityByYearMapper.getInput(sellerId, year)
    );

    var output = soldAndBuyProductQuantityByYearUseCase.execute(input);

    var datas = output.getOutputBoundary().getDatas();
    var yearRequest = output.getOutputBoundary().getRequestedYear();

    return soldAndBuyProductQuantityByYearMapper.mapToDto(datas, yearRequest);
  }

  @Override
  public SoldAndBuyProductPriceByYearResponseDto getSoldAndBuyProductPriceByYear(long sellerId, short year) {
    SoldAndBuyProductPriceByYearUseCase.Input input = new SoldAndBuyProductPriceByYearUseCase.Input(
            soldAndBuyProductPriceByYearMapper.getInput(sellerId, year)
    );

    var output = soldAndBuyProductPriceByYearUseCase.execute(input);

    var datas = output.getOutputBoundary().getDatas();
    var yearRequest = output.getOutputBoundary().getRequestedYear();

    return soldAndBuyProductPriceByYearMapper.mapToDto(datas, yearRequest);
  }
}
