package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductQuantityByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyProductQuantityByYearMapper {
  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductQuantityByYearMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * Map vers input
   * @param sellerId
   * @param yearRequest
   * @return
   */
  public ISoldAndBuyProductQuantityByYearInput getInput(long sellerId, short yearRequest) {
    return coreFactory.getSoldAndBuyProductQuantityByYearInputImpl(sellerId, yearRequest);
  }

  /**
   * Map vers output
   * @param datas
   * @param requestYear
   * @return
   */
  public ISoldAndBuyProductQuantityByYearOutput getOutput(List<ISoldAndBuyProductQuantityByYear> datas, short requestYear) {
    return coreFactory.getSoldAndBuyProductQuantityByYearOutputImpl(datas, requestYear);
  }

  /**
   * mapToSoldAndBuyProductQuantity
   * @param extractDatas
   * @return
   */
  public List<ISoldAndBuyProductQuantityByYear> mapToSoldAndBuyProductQuantity(List<Object[]> extractDatas) {
    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

    return extractDatas.stream()
            .map(result -> coreFactory.getSoldAndBuyProductQuantityByYearImpl(
                    ((Number) result[0]).intValue(), // Quantité vendu
                    (String) result[1], // Type de quantité (Achat ou vente)
                    String.valueOf(((Number) result[2]).intValue()) // année
            ))
            .collect(Collectors.toList());
  }
}
