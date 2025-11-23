package com.ctoutweb.monsuivi.core.port.getDesactivateSellerProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;

import java.util.List;

public interface GetDesactivateSellerProductsOutput {
    List<IProductSummarize> getAllProducts();
}
