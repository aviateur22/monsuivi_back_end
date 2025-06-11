package com.ctoutweb.monsuivi.core.port.filterSellerProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

import java.util.List;

public interface IFilterSellerProductsOutput extends IResponsMessage {
  List<IProductSummarize> getSellerFilterProducts();
}
