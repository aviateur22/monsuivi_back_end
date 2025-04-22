package com.ctoutweb.monsuivi.core.port.displayAllProducts;

import com.ctoutweb.monsuivi.core.entity.product.IProductSummarize;
import com.ctoutweb.monsuivi.core.entity.responseMessage.IResponsMessage;

import java.util.List;
public interface IGetAllProductsOutput extends IResponsMessage {
  List<IProductSummarize> getAllProducts();
}
