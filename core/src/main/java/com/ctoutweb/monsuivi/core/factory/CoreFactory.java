package com.ctoutweb.monsuivi.core.factory;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.chart.*;
import com.ctoutweb.monsuivi.core.entity.chart.impl.*;
import com.ctoutweb.monsuivi.core.entity.product.IProductDesactivate;
import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductDesacativateImpl;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductDetailImpl;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductSummarizeImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.impl.SoldAndBuyProductPriceByCategoryAndMonthInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.impl.SoldAndBuyProductPriceByCategoryAndMonthOutputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.impl.SoldAndBuyProductPriceBuyByCategoryAndYearInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.impl.SoldAndBuyProductPriceBuyByCategoryAndYearOutputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.impl.SoldAndBuyProductPriceByMonthInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.impl.SoldAndBuyProductPriceByMonthOutputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.impl.SoldAndBuyProductPriceByYearInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.impl.SoldAndBuyProductPriceByYearOutputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.impl.SoldAndBuyProductQuantityByCategoryAndYearInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.impl.SoldAndBuyProductQuantityByCategoryAndYearOutputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.impl.SoldAndBuyProductByCategoryByMonthOutputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.impl.SoldAndBuyProductByCategoryByMonthInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.impl.SoldAndBuyProductQuantityByMonthInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.impl.SoldAndBuyProductQuantityByMonthOutpuImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.impl.SoldAndBuyProductQuantityByYearInputImpl;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.impl.SoldAndBuyProductQuantityByYearOutputImpl;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductInput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.impl.DesactivateProductInputImpl;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.impl.DesactivateProductOutputImpl;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.impl.GetAllSellerProductsImpl;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailInput;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailOutput;
import com.ctoutweb.monsuivi.core.port.getProductDetail.impl.GetProductDetailInputImpl;
import com.ctoutweb.monsuivi.core.port.getProductDetail.impl.GetProductDetailOutputImpl;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateInput;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateOuput;
import com.ctoutweb.monsuivi.core.port.productUpdate.impl.ProductUpdateOutputImpl;
import com.ctoutweb.monsuivi.core.port.productUpdate.impl.UpdateProductInputImpl;

import java.time.LocalDate;
import java.util.List;

@CoreService
public class CoreFactory {
  public IProductSummarize getProductSummarizeImpl(
          long id,
          String imagePath,
          String productName,
          String statusCode,
          String categorycode){
    return new ProductSummarizeImpl(id, imagePath, productName, statusCode, categorycode);
  }

  public IGetAllProductsOutput getGetAllProductsOutputImpl(
          String responseMessage,
          List<IProductSummarize> products) {
    return new GetAllSellerProductsImpl(responseMessage, products);
  }

  public IDesactivateProductInput getDesactivateProductInputImpl(long productId, long sellerId) {
    return new DesactivateProductInputImpl(productId, sellerId);
  }
  public IProductDesactivate getProductDesactivate(long sellerId, long productId, boolean productIsActif) {
    return new ProductDesacativateImpl(sellerId, productId, productIsActif);
  }
  public IDesactivateProductOutput getDesactivateProductOutputImpl(
          String responseMessage,
          long sellerId,
          long productId,
          boolean productIsActif){
    return new DesactivateProductOutputImpl(responseMessage, sellerId, productId, productIsActif);
  }

  /**
   * GetDetailProduct -- Factory
   */
  public IGetProductDetailInput getProductDetailInputImpl(long productId, long sellerId) {
    return new GetProductDetailInputImpl(productId, sellerId);
  }

  public IGetProductDetailOutput getProductDetailOutputImpl(
          IProductDetail productDetail,
          long sellerId,
          String responseMessage) {
    return new GetProductDetailOutputImpl(productDetail, sellerId, responseMessage);
  }

  public IProductDetail getProductDetailImpl(
          long productid,
          String imagePath,
          double productPurchasePrice,
          String productName,
          LocalDate productBuyDay,
          LocalDate productSoldDay,
          double productSoldPrice,
          String productSatatus) {
    return new ProductDetailImpl(productid, imagePath, productPurchasePrice, productName, productBuyDay, productSoldDay, productSoldPrice, productSatatus);
  }

  /**
   * ProductUpdate -- Factory
   */
  public IProductUpdateInput getProductUpdateInput(
          Long productId,
          Long sellerId,
          double productPurchasePrice,
          double productSoldPrice,
          LocalDate productBuyDay,
          LocalDate productSoldDay,
          String productStatus
  ) {
    return new UpdateProductInputImpl(productId, sellerId, productPurchasePrice, productSoldPrice, productBuyDay, productSoldDay, productStatus);
  }

  public IProductUpdateOuput getProductUpdateOutput(IProductDetail productDetail, String responseMessage ){
    return new ProductUpdateOutputImpl(productDetail, responseMessage);
  }
  /**
   * Factory - SoldAndBuyProductQuantityByCategoryAndMonth
   */
  public ISoldAndBuyProductQuantityByCategoryAndMonth getSoldAndBuyProductQuantityByCategoryAndMonthImpl(
          String categoryName,
          String productBackgroundColor,
          String productTouchBackgroundColor,
          Integer buyQuantity,
          Integer soldQuantity
  ) {
    return new SoldAndBuyProductQuantityByCategoryAndMonthImpl(
            categoryName,
            productBackgroundColor,
            productTouchBackgroundColor,
            buyQuantity,
            soldQuantity
    );
  }

  public ISoldAndBuyProductByCategoryByMonthInput getSoldAndBuyProductByCategoryByMonthInputImpl(long sellerId, short month, short year) {
    return new SoldAndBuyProductByCategoryByMonthInputImpl(sellerId, year, month);
  }

  public ISoldAndBuyProductByCategoryByMonthOutput getChartByProductTypeMonthOutputImpl(
          List<ISoldAndBuyProductQuantityByCategoryAndMonth> datas,
          short year,
          String month) {
    return new SoldAndBuyProductByCategoryByMonthOutputImpl(datas, year, month);
  }

  /**
   * Factory - SoldAndBuyProductPriceByCategoryAndMonth
   */
  public ISoldAndBuyProductPriceByCategoryAndMonthInput getSoldAndBuyProductPriceByCategoryAndMonthInputImpl(long sellerId, short month, short year) {
    return new SoldAndBuyProductPriceByCategoryAndMonthInputImpl(sellerId, year, month);
  }

  public ISoldAndBuyProductPriceByCategoryAndMonthOutput getSoldAndBuyProductPriceByCategoryAndMonthOutputImpl(
          List<ISoldAndBuyProductPriceByCategoryAndMonth> datas,
          short year,
          String month) {
    return new SoldAndBuyProductPriceByCategoryAndMonthOutputImpl(datas, year, month);
  }

  public ISoldAndBuyProductPriceByCategoryAndMonth getSoldAndBuyProductPriceByCategoryAndMonth(
          String categoryName,
          String productBackgroundColor,
          String productTouchBackgroundColor,
          double buyPrice,
          double soldPrice
  ) {
    return new SoldAndBuyProductPriceByCategoryAndMonthImpl(
            categoryName,
            productBackgroundColor,
            productTouchBackgroundColor,
            buyPrice,
            soldPrice
    );
  }

  /**
   * Factory - SoldAndBuyProductPriceByCategoryAndYear
   */
  public ISoldAndBuyProductPriceBuyByCategoryAndYearInput getSoldAndBuyProductPriceBuyByCategoryAndYearInputImpl(
          long sellerId,
          short year) {
    return new SoldAndBuyProductPriceBuyByCategoryAndYearInputImpl(sellerId, year);
  }

  public ISoldAndBuyProductPriceBuyByCategoryAndYearOutput getSoldAndBuyProductPriceBuyByCategoryAndYearOutputImpl(
          List<ISoldAndBuyProductPriceByCategoryAndYear> datas,
          short year
  ) {
    return new SoldAndBuyProductPriceBuyByCategoryAndYearOutputImpl(datas, year);
  }
  public ISoldAndBuyProductPriceByCategoryAndYear getSoldAndBuyProductPriceByCategoryAndYearImpl(
          String categoryName,
          String productBackgroundColor,
          String productTouchBackgroundColor,
          String year,
          double buyPrice,
          double soldPrice
  ) {
    return new SoldAndBuyProductPriceByCategoryAndYearImpl(
            categoryName,
            productBackgroundColor,
            productTouchBackgroundColor,
            year,
            buyPrice,
            soldPrice
    );
  }

  /**
   * Factory - SoldAndByProductQuantityByCategoryAndYear
   */
  public ISoldAndBuyProductQuantityByCategoryAndYearInput getSoldAndBuyProductQuantityByCategoryAndYearInputImpl(
          long sellerId,
          short year) {
    return new SoldAndBuyProductQuantityByCategoryAndYearInputImpl(sellerId, year);
  }

  public ISoldAndBuyProductQuantityByCategoryAndYearOutput getSoldAndBuyProductQuantityByCategoryAndYearOutputImpl(
          List<ISoldAndBuyProductQuantityByCategoryAndYear> datas,
          short year
  ) {
    return new SoldAndBuyProductQuantityByCategoryAndYearOutputImpl(datas, year);
  }

  public ISoldAndBuyProductQuantityByCategoryAndYear getSoldAndBuyProductQuantityByCategoryAndYearImpl(
          String categoryName,
          String productBackgroundColor,
          String productTouchBackgroundColor,
          String year,
          int buyQuantity,
          int soldQuantity
  ) {
    return new SoldAndBuyProductQuantityByCategoryAndYearImpl(
            categoryName,
            productBackgroundColor,
            productTouchBackgroundColor,
            year,
            buyQuantity,
            soldQuantity
    );
  }

  /**
   * Factory - SoldAndByProductQuantityByYear
   */
  public ISoldAndBuyProductQuantityByYearInput getSoldAndBuyProductQuantityByYearInputImpl(long sellerId, short yearRequest) {
    return new SoldAndBuyProductQuantityByYearInputImpl(sellerId, yearRequest);
  }

  public ISoldAndBuyProductQuantityByYearOutput getSoldAndBuyProductQuantityByYearOutputImpl(
          List<ISoldAndBuyProductQuantityByYear> datas,
          short requestYear) {
    return new SoldAndBuyProductQuantityByYearOutputImpl(datas, requestYear);
  }

  public ISoldAndBuyProductQuantityByYear getSoldAndBuyProductQuantityByYearImpl(
          int totalQuantity,
          String quantityType,
          String year
  ) {
    return new SoldAndBuyProductQuantityByYearImpl(quantityType, totalQuantity, year);
  }

  /**
   * Factory - SoldAndByProductPriceByYear
   */
  public ISoldAndBuyProductPriceByYearInput getSoldAndBuyProductPriceByYearInputImpl(long sellerId, short yearRequest) {
    return new SoldAndBuyProductPriceByYearInputImpl(sellerId, yearRequest);
  }

  public ISoldAndBuyProductPriceByYearOutput getSoldAndBuyProductPriceByYearOutputImpl(
          List<ISoldAndBuyProductPriceByYear> datas,
          short requestYear
  ) {
    return new SoldAndBuyProductPriceByYearOutputImpl(datas, requestYear);

  }
  public ISoldAndBuyProductPriceByYear getSoldAndBuyProductPriceByYearImpl(
          double totalPrice,
          String priceType,
          String requestedYear
  ) {
    return new SoldAndBuyProductPriceByYearImpl(
            priceType,
            totalPrice,
            requestedYear
    );
  }

  /**
   * Factory - ISoldAndBuyProductPriceByMonth
   */
  public ISoldAndBuyProductPriceByMonthInput getSoldAndBuyProductPriceByMonthInputImpl(
          long sellerId,
          short year,
          short month
  ) {
    return new SoldAndBuyProductPriceByMonthInputImpl(sellerId, year, month);
  }

  public ISoldAndBuyProductPriceByMonthOutput getSoldAndBuyProductPriceByMonthOutputImpl(
          List<ISoldAndBuyProductPriceByMonth> datas,
          String requestedMonth,
          short requestedYear
  ) {
    return new SoldAndBuyProductPriceByMonthOutputImpl(
            datas,
            requestedMonth,
            requestedYear
    );
  }

  public ISoldAndBuyProductPriceByMonth getSoldAndBuyProductPriceByMonthImpl(
          String monthWithYear,
          String priceType,
          Double totalPrice
  ) {
    return new SoldAndBuyProductPriceByMonthImpl(monthWithYear, priceType, totalPrice);
  }

  /**
   * Factory - ISoldAndBuyProductQuantityByMonth
   */
  public ISoldAndBuyProductQuantityByMonthInput getSoldAndBuyProductQuantityByMonthInputImpl(
          long sellerId,
          short year,
          short month
  ) {
    return new SoldAndBuyProductQuantityByMonthInputImpl(sellerId, year, month);
  }

  public ISoldAndBuyProductQuantityByMonthOutput getSoldAndBuyProductQuantityByMonthOutputImpl(
          List<ISoldAndBuyProductQuantityByMonth> datas,
          String requestedMonth,
          short requestedYear
  ) {
    return new SoldAndBuyProductQuantityByMonthOutpuImpl(
            datas,
            requestedMonth,
            requestedYear
    );
  }
  public ISoldAndBuyProductQuantityByMonth getSoldAndBuyProductQuantityByMonthImpl(
          String monthWithYear,
          String quantityType,
          Integer totalQuantity
  ) {
    return new SoldAndBuyProductQuantityByMonthImpl(monthWithYear, quantityType, totalQuantity);
  }
}
