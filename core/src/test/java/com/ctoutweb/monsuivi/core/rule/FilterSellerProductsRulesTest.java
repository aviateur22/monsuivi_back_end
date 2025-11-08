package com.ctoutweb.monsuivi.core.rule;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.entity.product.impl.ProductDetailImpl;
import com.ctoutweb.monsuivi.core.port.common.ISellerProductsManagerGateway;
import com.ctoutweb.monsuivi.core.rule.impl.SellerProductsManagerRuleImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

public class FilterSellerProductsRulesTest {

  @Mock
  ISellerProductsManagerGateway filterSellerProductsGatewayMock;

  @BeforeEach
  public void initTest() {
    MockitoAnnotations.openMocks(this);
  }
  @Test
  public void filterByRegisterPeriod() {
    /**
     * given
     */
      Short nullPeriod = null;
      short oneWeekPeriod = (short) 7;
      short oneMonthPeriod = (short) 31;
      short twoMonthPeriod = (short) 60;
      short threeMonthPeriod = (short) 90;

      Mockito.when(filterSellerProductsGatewayMock.getAllSellerProducts(Mockito.anyLong())).thenReturn(getProductsListToTestFilterByPeriodInDay());
      Mockito.when(filterSellerProductsGatewayMock.isSellerFind(Mockito.anyLong())).thenReturn(true);
      ISellerProductsManagerRules filterSellerProductsRules = new SellerProductsManagerRuleImpl(filterSellerProductsGatewayMock);

    /**
     * when - nullPeriod
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByRegisterPeriod(nullPeriod);

    List<IProductSummarize> actualNullPeriodFilteredProduct = filterSellerProductsRules.getProducts();

    /**
     * when - oneWeekPeriod
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByRegisterPeriod(oneWeekPeriod);

    List<IProductSummarize> actualOneWeekPeriodFilteredProduct = filterSellerProductsRules.getProducts();

    /**
     * when - oneMonthPeriod
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByRegisterPeriod(oneMonthPeriod);

    List<IProductSummarize> actualoneMonthPeriodFilteredProduct = filterSellerProductsRules.getProducts();

    /**
     * when - twoMonthPeriod
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByRegisterPeriod(twoMonthPeriod);

    List<IProductSummarize> actualTwoMonthPeriodFilteredProduct = filterSellerProductsRules.getProducts();

    /**
     * when - threeMonthPeriod
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByRegisterPeriod(threeMonthPeriod);

    List<IProductSummarize> actualThreeMonthPeriodFilteredProduct = filterSellerProductsRules.getProducts();

    /**
     * then
     */
    Assertions.assertEquals(5, actualNullPeriodFilteredProduct.size());

    Assertions.assertEquals(2, actualoneMonthPeriodFilteredProduct.size());
    Assertions.assertEquals("Product A", actualoneMonthPeriodFilteredProduct.get(0).getProductName());
    Assertions.assertEquals("Product C", actualoneMonthPeriodFilteredProduct.get(1).getProductName());

    Assertions.assertEquals(3, actualTwoMonthPeriodFilteredProduct.size());
    Assertions.assertEquals("Product A", actualTwoMonthPeriodFilteredProduct.get(0).getProductName());
    Assertions.assertEquals("Product C", actualTwoMonthPeriodFilteredProduct.get(1).getProductName());
    Assertions.assertEquals("Product D", actualTwoMonthPeriodFilteredProduct.get(2).getProductName());

    Assertions.assertEquals(4, actualThreeMonthPeriodFilteredProduct.size());
    Assertions.assertEquals("Product A", actualThreeMonthPeriodFilteredProduct.get(0).getProductName());
    Assertions.assertEquals("Product C", actualThreeMonthPeriodFilteredProduct.get(1).getProductName());
    Assertions.assertEquals("Product D", actualThreeMonthPeriodFilteredProduct.get(2).getProductName());
    Assertions.assertEquals("Product E", actualThreeMonthPeriodFilteredProduct.get(3).getProductName());
  }

  @Test
  public void filterByProductCategory() {
    /**
     * given
     */
    String nullCategory = null;
    Mockito.when(filterSellerProductsGatewayMock.getAllSellerProducts(Mockito.anyLong())).thenReturn(getProductsListToTestFilterByCategory());
    Mockito.when(filterSellerProductsGatewayMock.isSellerFind(Mockito.anyLong())).thenReturn(true);
    ISellerProductsManagerRules filterSellerProductsRules = new SellerProductsManagerRuleImpl(filterSellerProductsGatewayMock);

    String bookCategory = "bk";
    String clotheCategory = "cl";
    String gameCategory = "ga";
    /**
     * when - nullCategory
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductCategory(nullCategory);

    List<IProductSummarize> actualnullCategoryilteredProduct = filterSellerProductsRules.getProducts();

    /**
     * when - bookCategory
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductCategory(bookCategory);

    List<IProductSummarize> actualBookCategoryFilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - clotheCategory
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductCategory(clotheCategory);

    List<IProductSummarize> actualClotheCategoryFilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - gameCategory
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductCategory(gameCategory);

    List<IProductSummarize> actualGameCategoryFilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * then
     */
    Assertions.assertEquals(9, actualnullCategoryilteredProduct.size());

    Assertions.assertEquals(2, actualBookCategoryFilteredProducts.size());
    Assertions.assertEquals("Product C", actualBookCategoryFilteredProducts.get(0).getProductName());
    Assertions.assertEquals("Product D", actualBookCategoryFilteredProducts.get(1).getProductName());

    Assertions.assertEquals(5, actualClotheCategoryFilteredProducts.size());
    Assertions.assertEquals("Product E", actualClotheCategoryFilteredProducts.get(0).getProductName());

    Assertions.assertEquals(2, actualGameCategoryFilteredProducts.size());
    Assertions.assertEquals("Product A", actualGameCategoryFilteredProducts.get(0).getProductName());
    Assertions.assertEquals("Product B", actualGameCategoryFilteredProducts.get(1).getProductName());
  }

  @Test

  public void filterByName() {
    /**
     * given
     */
    String nullName = null;
    String name1 = "pro";
    String name2 = "product";
    String name3 = "PRODUCT";
    String name4 = "product E";
    String name5 = "PRoduct e";
    String name6 = "toto";
    String name7 = "lélé";
    String name8 = "lelé";
    String name9 = "lele";
    String name10 = "lelel";

    Mockito.when(filterSellerProductsGatewayMock.getAllSellerProducts(Mockito.anyLong())).thenReturn(getProductsListToTestFilterByCategory());
    Mockito.when(filterSellerProductsGatewayMock.isSellerFind(Mockito.anyLong())).thenReturn(true);
    ISellerProductsManagerRules filterSellerProductsRules = new SellerProductsManagerRuleImpl(filterSellerProductsGatewayMock);



    /**
     * when - name is null
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(nullName);

    List<IProductSummarize> actualNullNameFilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is pro
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name1);

    List<IProductSummarize> actualName1FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is product
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name2);

    List<IProductSummarize> actualName2FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * When - name is PRODUCT
     */

    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name3);

    List<IProductSummarize> actualName3FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is product E
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name4);

    List<IProductSummarize> actualName4FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is PRoduct E
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name5);

    List<IProductSummarize> actualName5FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is toto
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name6);

    List<IProductSummarize> actualName6FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is lélé
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name7);

    List<IProductSummarize> actualName7FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is lelé
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name8);

    List<IProductSummarize> actualName8FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is lele
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name9);

    List<IProductSummarize> actualName9FilteredProducts = filterSellerProductsRules.getProducts();

    /**
     * when - name is lelel
     */
    filterSellerProductsRules.initialiseRule();
    filterSellerProductsRules.getSellerProducts(Mockito.anyLong());
    filterSellerProductsRules.filterByProductName(name10);

    List<IProductSummarize> actualName10FilteredProducts = filterSellerProductsRules.getProducts();


    /**
     * then
     */
    Assertions.assertEquals(9, actualNullNameFilteredProducts.size());
    Assertions.assertEquals(5, actualName1FilteredProducts.size());
    Assertions.assertEquals(5, actualName2FilteredProducts.size());
    Assertions.assertEquals(5, actualName3FilteredProducts.size());
    Assertions.assertEquals(1, actualName4FilteredProducts.size());
    Assertions.assertEquals(1, actualName5FilteredProducts.size());
    Assertions.assertEquals(4, actualName7FilteredProducts.size());
    Assertions.assertEquals(4, actualName8FilteredProducts.size());
    Assertions.assertEquals(4, actualName9FilteredProducts.size());
    Assertions.assertEquals(0, actualName10FilteredProducts.size());

    Assertions.assertTrue(actualName6FilteredProducts.isEmpty());
  }

  public List<IProductDetail> getProductsListToTestFilterByPeriodInDay() {
    return List.of(
            new ProductDetailImpl(1, "/images/1.png", 100.0, "Product A", LocalDate.now(), LocalDate.of(2023, 2, 10), 150.0, "SOLD", "ELEC"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "Product B", LocalDate.now().minusMonths(6), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "HOME"),
            new ProductDetailImpl(3, "/images/3.png", 300.0, "Product C", LocalDate.now().minusDays(10), null, 0.0, "AVAILABLE", "ELEC"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "Product D", LocalDate.now().minusDays(45), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "HOME"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "Product E", LocalDate.now().minusDays(62), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "HOME")
    );
  }
  public List<IProductDetail> getProductsListToTestFilterByCategory() {
    return List.of(
            new ProductDetailImpl(1, "/images/1.png", 100.0, "Product A", LocalDate.now(), LocalDate.of(2023, 2, 10), 150.0, "SOLD", "ga"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "Product B", LocalDate.now().minusMonths(6), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "ga"),
            new ProductDetailImpl(3, "/images/3.png", 300.0, "Product C", LocalDate.now().minusDays(10), null, 0.0, "AVAILABLE", "bk"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "Product D", LocalDate.now().minusDays(45), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "bk"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "Product E", LocalDate.now().minusDays(62), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "cl"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "lélé E", LocalDate.now().minusDays(62), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "cl"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "pleleE", LocalDate.now().minusDays(62), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "cl"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "leleE", LocalDate.now().minusDays(62), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "cl"),
            new ProductDetailImpl(2, "/images/2.png", 200.0, "eléléE", LocalDate.now().minusDays(62), LocalDate.of(2023, 2, 15), 250.0, "SOLD", "cl")
    );
  }
}
