package com.ctoutweb.monsuivi.infra.repository.impl;

import com.ctoutweb.monsuivi.infra.repository.IChartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
public class IChartRepositoryImpl implements IChartRepository {
  @PersistenceContext
  private EntityManager entityManager;
  @Override
  public List<Object[]> getSoldAndBuyProductQuantityiesByCategoryAndMonthList(Long sellerId, String date) {

    String sql = "WITH select_month_buy AS ("+
     "SELECT DISTINCT DATE_TRUNC('month', sc_monsuivi.product.product_buy_at) AS month "+
            "FROM sc_monsuivi.product "+
            "WHERE sc_monsuivi.product.seller_id = :sellerId "+
            "AND to_char(DATE_TRUNC('month', sc_monsuivi.product.product_buy_at), 'YYYY-MM') = :date"+
    "),"+
            "select_month_sold AS ("+
            "SELECT DISTINCT DATE_TRUNC('month', sc_monsuivi.product.product_sold_at) AS month "+
            "FROM sc_monsuivi.product "+
            "WHERE sc_monsuivi.product.seller_id = :sellerId "+
            "AND to_char(DATE_TRUNC('month', sc_monsuivi.product.product_sold_at), 'YYYY-MM') = :date"+
    "), "+
            "product_category AS ("+
            "SELECT category_code AS product_category, category_name, pcc.color  as color, pcc.touch_color as touch_color "+
            "FROM sc_monsuivi.product_category pc "+
            "LEFT JOIN "+
            "sc_monsuivi.product_category_color pcc "+
            "ON pc.id = pcc.category_id "+
    "), "+
            "sold_quantity AS ("+
            "SELECT "+
            "p.product_category,"+
            "DATE_TRUNC('month', p.product_sold_at) AS month,"+
            "COUNT(*) AS sold_quantity_product "+
    "FROM "+
    "sc_monsuivi.product p "+
    "WHERE "+
    "p.seller_id = :sellerId "+
    "AND to_char(DATE_TRUNC('month', p.product_sold_at), 'YYYY-MM') = :date "+
    "GROUP BY "+
    "p.product_category, DATE_TRUNC('month', p.product_sold_at) "+
	") "+
    "SELECT "+
    "pc.product_category,"+
            "pc.category_name,"+
            "color,"+
            "touch_color,"+
            "to_char(smb.month, 'YYYY-MM') AS month,"+
    "COUNT(CASE WHEN p_buy.product_category IS NOT NULL THEN 1 END) AS buy_quantity_product,"+
    "COALESCE(sq.sold_quantity_product, 0) AS sold_quantity_product "+
    "FROM "+
    "product_category pc "+
    "CROSS JOIN "+
    "select_month_buy smb "+
    "LEFT JOIN "+
    "sc_monsuivi.product p_buy "+
    "ON p_buy.product_category = pc.product_category "+
    "AND p_buy.seller_id = :sellerId "+
    "AND DATE_TRUNC('month', p_buy.product_buy_at) = smb.month "+
    "LEFT JOIN "+
    "sold_quantity sq "+
    "ON sq.product_category = pc.product_category "+
    "AND sq.month = smb.month "+
    "GROUP BY "+
    "pc.product_category, pc.category_name, color, touch_color, smb.month, sq.sold_quantity_product "+
    "ORDER BY "+
    "pc.product_category";

    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("sellerId", sellerId);
    query.setParameter("date", date);

    List<Object[]> results = query.getResultList();
    return  results;
  }

  @Override
  public List<Object[]> getSoldAndBuyProductsPricesByCategoryAndMonthList(Long sellerId, String date) {

    String sql = "WITH select_month_buy AS (" +
            "SELECT DISTINCT DATE_TRUNC('month', sc_monsuivi.product.product_buy_at) AS month " +
            "FROM sc_monsuivi.product " +
            "WHERE sc_monsuivi.product.seller_id = :sellerId " +
            "AND to_char(DATE_TRUNC('month', sc_monsuivi.product.product_buy_at), 'YYYY-MM') = :date)," +
            " select_month_sold AS (" +
            "SELECT DISTINCT DATE_TRUNC('month', sc_monsuivi.product.product_sold_at) AS month " +
            "FROM sc_monsuivi.product " +
            "WHERE sc_monsuivi.product.seller_id = :sellerId " +
            "AND to_char(DATE_TRUNC('month', sc_monsuivi.product.product_sold_at), 'YYYY-MM') = :date)," +
            "product_category AS (" +
            "SELECT category_code AS product_category, category_name, pcc.color  as color, pcc.touch_color as touch_color " +
            "FROM sc_monsuivi.product_category pc " +
            "LEFT JOIN " +
            "sc_monsuivi.product_category_color pcc " +
            "ON pc.id = pcc.category_id " +
            ")," +
            "sold_quantity AS (" +
            "SELECT " +
            "p.product_category," +
            "DATE_TRUNC('month', p.product_sold_at) AS month," +
            "sum(p.product_sold_price) AS sold_price_product " +
            "FROM " +
            "sc_monsuivi.product p " +
            "WHERE " +
            "p.seller_id = :sellerId " +
            "AND to_char(DATE_TRUNC('month', p.product_sold_at), 'YYYY-MM') = :date " +
            "GROUP BY " +
            "p.product_category, DATE_TRUNC('month', p.product_sold_at)" +
            ")" +
            "SELECT " +
            "pc.product_category," +
            "pc.category_name," +
            "color," +
            "touch_color," +
            "to_char(smb.month, 'YYYY-MM') AS month," +
            "sum(CASE WHEN p_buy.product_category IS NOT NULL THEN p_buy.product_purchase_price END) AS buy_price_product," +
            "COALESCE(sq.sold_price_product, 0) AS sold_price_product " +
            "FROM " +
            "product_category pc " +
            "CROSS JOIN " +
            "select_month_buy smb " +
            "LEFT JOIN " +
            "sc_monsuivi.product p_buy " +
            "ON p_buy.product_category = pc.product_category " +
            "AND p_buy.seller_id = :sellerId " +
            "AND DATE_TRUNC('month', p_buy.product_buy_at) = smb.month " +
            "LEFT JOIN " +
            "sold_quantity sq " +
            "ON sq.product_category = pc.product_category " +
            "AND sq.month = smb.month " +
            "GROUP BY " +
            "pc.product_category, color, touch_color, pc.category_name, smb.month, sq.sold_price_product " +
            "ORDER BY " +
            "product_category;";

    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("sellerId", sellerId);
    query.setParameter("date", date);

    List<Object[]> results = query.getResultList();
    return  results;
  }

  @Override
  public List<Object[]> getSoldAndBuyProductPricesByCategoryAndYearList(Long sellerId, short year) {
    String sql = "WITH select_month_buy AS (\n" +
            "    SELECT DISTINCT DATE_TRUNC('year', sc_monsuivi.product.product_buy_at) AS year\n" +
            "    FROM sc_monsuivi.product\n" +
            "    WHERE sc_monsuivi.product.seller_id = 1\n" +
            "    AND DATE_PART('year', sc_monsuivi.product.product_buy_at) = :year\n" +
            "),\n" +
            "select_month_sold AS (\n" +
            "    SELECT DISTINCT DATE_TRUNC('year', sc_monsuivi.product.product_sold_at) AS year\n" +
            "    FROM sc_monsuivi.product\n" +
            "    WHERE sc_monsuivi.product.seller_id = :sellerId \n" +
            "    AND DATE_PART('year', sc_monsuivi.product.product_sold_at) = :year\n" +
            "),\n" +
            "product_category AS (\n" +
            "  SELECT category_code AS product_category, category_name, pcc.color  as color, pcc.touch_color as touch_color\n" +
            "\t    FROM sc_monsuivi.product_category pc\n" +
            "\t    LEFT JOIN\n" +
            "\t    sc_monsuivi.product_category_color pcc \n" +
            "\t    ON pc.id = pcc.category_id\n" +
            "),\n" +
            "sold_quantity AS (\n" +
            "    SELECT\n" +
            "        p.product_category,\n" +
            "        DATE_TRUNC('year', p.product_sold_at) AS year,\n" +
            "        sum(p.product_sold_price) AS sold_price_product\n" +
            "    FROM\n" +
            "        sc_monsuivi.product p\n" +
            "    WHERE\n" +
            "        p.seller_id = :sellerId \n" +
            "        AND DATE_PART('year', p.product_sold_at) = :year\n" +
            "    GROUP BY\n" +
            "        p.product_category, DATE_TRUNC('year', p.product_sold_at)\n" +
            ")\n" +
            "SELECT\n" +
            "    pc.product_category,\n" +
            "    pc.category_name,\n" +
            "    color,\n" +
            "\ttouch_color,\n" +
            "    to_char(smb.year, 'YYYY') AS year,\n" +
            " \tsum(CASE WHEN p_buy.product_category IS NOT NULL THEN p_buy.product_purchase_price END) AS buy_price_product,\n" +
            "    COALESCE(sq.sold_price_product, 0) AS sold_price_product\n" +
            "FROM\n" +
            "    product_category pc\n" +
            "CROSS JOIN\n" +
            "    select_month_buy smb\n" +
            "LEFT JOIN\n" +
            "    sc_monsuivi.product p_buy\n" +
            "    ON p_buy.product_category = pc.product_category\n" +
            "    AND p_buy.seller_id = :sellerId \n" +
            "    AND DATE_TRUNC('year', p_buy.product_buy_at) = smb.year\n" +
            "LEFT JOIN\n" +
            "    sold_quantity sq\n" +
            "    ON sq.product_category = pc.product_category\n" +
            "    AND sq.year = smb.year\n" +
            "GROUP BY\n" +
            "    pc.product_category, color, touch_color, pc.category_name, smb.year, sq.sold_price_product\n" +
            "ORDER BY\n" +
            "    product_category;";

    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("sellerId", sellerId);
    query.setParameter("year", year);

    List<Object[]> results = query.getResultList();
    return  results;
  }

  @Override
  public List<Object[]> getSoldAndBuyProductQuantitiesByCategoryAndYearList(Long sellerId, short year) {
    String sql = "WITH select_month_buy AS (\n" +
            "    SELECT DISTINCT DATE_TRUNC('year', sc_monsuivi.product.product_buy_at) AS year\n" +
            "    FROM sc_monsuivi.product\n" +
            "    WHERE sc_monsuivi.product.seller_id = :sellerId\n" +
            "    AND DATE_PART('year', sc_monsuivi.product.product_buy_at) = :year\n" +
            "),\n" +
            "select_month_sold AS (\n" +
            "    SELECT DISTINCT DATE_TRUNC('year', sc_monsuivi.product.product_sold_at) AS year\n" +
            "    FROM sc_monsuivi.product\n" +
            "    WHERE sc_monsuivi.product.seller_id = :sellerId\n" +
            "    AND DATE_PART('year', sc_monsuivi.product.product_sold_at) = :year\n" +
            "),\n" +
            "product_category AS (\n" +
            "  SELECT category_code AS product_category, category_name, pcc.color  as color, pcc.touch_color as touch_color\n" +
            "\t    FROM sc_monsuivi.product_category pc\n" +
            "\t    LEFT JOIN\n" +
            "\t    sc_monsuivi.product_category_color pcc \n" +
            "\t    ON pc.id = pcc.category_id\n" +
            "),\n" +
            "sold_quantity AS (\n" +
            "    SELECT\n" +
            "        p.product_category,\n" +
            "        DATE_TRUNC('year', p.product_sold_at) AS year,\n" +
            "        COUNT(*) AS sold_quantity_product\n" +
            "    FROM\n" +
            "        sc_monsuivi.product p\n" +
            "    WHERE\n" +
            "        p.seller_id = :sellerId\n" +
            "        AND DATE_PART('year', p.product_sold_at) = :year\n" +
            "    GROUP BY\n" +
            "        p.product_category, DATE_TRUNC('year', p.product_sold_at)\n" +
            ")\n" +
            "SELECT\n" +
            "    pc.product_category,\n" +
            "    pc.category_name,\n" +
            "    color,\n" +
            "\ttouch_color,\n" +
            "    to_char(smb.year, 'YYYY') AS year,\n" +
            " \tCOUNT(CASE WHEN p_buy.product_category IS NOT NULL THEN 1 END) AS buy_quantity_product,\n" +
            "    COALESCE(sq.sold_quantity_product, 0) AS sold_quantity_product\n" +
            "FROM\n" +
            "    product_category pc\n" +
            "CROSS JOIN\n" +
            "    select_month_buy smb\n" +
            "LEFT JOIN\n" +
            "    sc_monsuivi.product p_buy\n" +
            "    ON p_buy.product_category = pc.product_category\n" +
            "    AND p_buy.seller_id = :sellerId\n" +
            "    AND DATE_TRUNC('year', p_buy.product_buy_at) = smb.year\n" +
            "LEFT JOIN\n" +
            "    sold_quantity sq\n" +
            "    ON sq.product_category = pc.product_category\n" +
            "    AND sq.year = smb.year\n" +
            "GROUP BY\n" +
            "    pc.product_category, color, touch_color, pc.category_name, smb.year, sq.sold_quantity_product\n" +
            "ORDER BY\n" +
            "    product_category;    \n";

    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("sellerId", sellerId);
    query.setParameter("year", year);

    List<Object[]> results = query.getResultList();
    return  results;
  }

  @Override
  public List<Object[]> getSoldAndBuyProductQuantityByYearList(Long sellerId, short year) {
    String sql = "with product_sold as(\n" +
            "select \t\t\n" +
            "\tsum(\n" +
            "\t\tcase \n" +
            "\t\t\twhen (p.seller_id = :sellerId and DATE_PART('year', p.product_sold_at) = :year)\n" +
            "\t\t\tthen 1\n" +
            "\t\t\telse 0\n" +
            "\t\tend\n" +
            "\t\t) as total_quantity,\n" +
            "\t\t'quantité vendue' as quantity_type,\n" +
            "\t\t:year as year\n" +
            "\t\tfrom sc_monsuivi.product p\n" +
            "),\n" +
            "product_buy as (select\n" +
            "\n" +
            "\tsum(\n" +
            "\tcase \n" +
            "\t\twhen (p.seller_id = :sellerId and DATE_PART('year', p.product_buy_at) = :year)\n" +
            "\t\tthen 1  \n" +
            "\t\telse 0\n" +
            "\tend\n" +
            "\t) as total_quantity,\n" +
            "\t'quantité achetée' as quantity_type,\n" +
            "\t:year as year\n" +
            "\tfrom sc_monsuivi.product p\n" +
            ")\n" +
            "SELECT * FROM product_buy\n" +
            "UNION ALL\n" +
            "SELECT * FROM product_sold;";

    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("sellerId", sellerId);
    query.setParameter("year", year);

    List<Object[]> results = query.getResultList();
    return  results;
  }

  @Override
  public List<Object[]> getSoldAndBuyProductPriceByYearList(Long sellerId, short year) {
    String sql = "with product_sold as(select \t\t\n" +
            "\tsum(\n" +
            "\t\tcase \n" +
            "\t\t\twhen (p.seller_id = :sellerId and DATE_PART('year', p.product_sold_at) = :year)\n" +
            "\t\t\tthen p.product_sold_price \n" +
            "\t\t\telse 0\n" +
            "\t\tend\n" +
            "\t\t) as total_price,\n" +
            "\t\t'prix de vente' as price_type,\n" +
            "\t\t:year as year\n" +
            "\t\tfrom sc_monsuivi.product p\n" +
            "\t), product_buy as (select\n" +
            "\tsum(\n" +
            "\tcase \n" +
            "\t\twhen (p.seller_id = :sellerId and DATE_PART('year', p.product_buy_at) = :year)\n" +
            "\t\tthen p.product_purchase_price  \n" +
            "\t\telse 0\n" +
            "\tend\n" +
            "\t) as total_price,\n" +
            "\t'prix d''achat' as price_type,\n" +
            "\t\t:year as year\n" +
            "\tfrom sc_monsuivi.product p\t\n" +
            "\t)\n" +
            "select * from product_sold\n" +
            "union all\n" +
            "select * from product_buy";

    Query query = entityManager.createNativeQuery(sql);
    query.setParameter("sellerId", sellerId);
    query.setParameter("year", year);

    List<Object[]> results = query.getResultList();
    return  results;
  }
}
