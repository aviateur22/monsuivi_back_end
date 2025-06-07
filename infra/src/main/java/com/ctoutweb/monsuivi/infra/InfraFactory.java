package com.ctoutweb.monsuivi.infra;

import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.doughnut.ChartJsDoughnut;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.doughnut.DoughnutDataSet;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.ChartJsStackedBar;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.StackedBarDataSet;
import com.ctoutweb.monsuivi.infra.model.chart.impl.ChartImpl;
import com.ctoutweb.monsuivi.infra.model.image.IImageToSave;
import com.ctoutweb.monsuivi.infra.model.image.impl.ImageToSaveImpl;
import com.ctoutweb.monsuivi.infra.model.error.ErrorMessageImpl;
import com.ctoutweb.monsuivi.infra.model.error.IErrorMessage;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import com.ctoutweb.monsuivi.infra.model.message.chart.impl.ChartDataResponseImpl;
import com.ctoutweb.monsuivi.infra.model.product.IProductCategory;
import com.ctoutweb.monsuivi.infra.model.product.IProductStatus;
import com.ctoutweb.monsuivi.infra.model.product.ISummarizeProduct;
import com.ctoutweb.monsuivi.infra.model.product.impl.ProductCategoryImpl;
import com.ctoutweb.monsuivi.infra.model.product.impl.ProductStatusImpl;
import com.ctoutweb.monsuivi.infra.model.product.impl.SummarizeProductImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InfraFactory {

  public ISummarizeProduct getProductSummarizeImpl(
          long id,
          String title,
          IProductCategory category,
          IProductStatus productStatus,
          String imageToshow) {
    return new SummarizeProductImpl(id,title, category, productStatus, imageToshow);
  }

  public IProductCategory getProductCategoryImpl(String code, String category) {
    return new ProductCategoryImpl(code, category);
  }

  public IProductStatus getProductStatusImpl(String code, String status) {
    return new ProductStatusImpl(code, status);
  }
  public IImageToSave getImageToSaveImpl() {
    return new ImageToSaveImpl();
  }

  public IErrorMessage getErrorMessageImpl(String errorMessage) {
    return new ErrorMessageImpl(errorMessage);
  }

  /**
   * chart
   */

  public <T> ChartJsDoughnut getChartJsDoughnut(
          List<String> labels,
          List<String> backgroundColors,
          List<String> touchBackgroundColors,
          List<T> values
  ) {
    var doughnutData = new DoughnutDataSet<>(backgroundColors, touchBackgroundColors, values);
    return new ChartJsDoughnut(labels, doughnutData);
  }

  public ChartJsStackedBar getStackedBar(
          List<String> axisLabels,
          List<StackedBarDataSet> stackedBarDataset
  ) {
    return new ChartJsStackedBar(axisLabels, stackedBarDataset);
  }

  public <T> IChartDataResponse getChartDataResponseImpl(T chartData, String message) {
    var chartDataImpl = new ChartImpl(chartData);
    return new ChartDataResponseImpl(chartDataImpl, message);
  }
 }
