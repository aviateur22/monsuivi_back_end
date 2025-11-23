package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.port.common.ISellerProductsManagerGateway;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;
import com.ctoutweb.monsuivi.core.port.getDesactivateSellerProducts.GetDesactivateSellerProductsInput;
import com.ctoutweb.monsuivi.core.rule.ISellerProductsManagerRules;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;

@CoreService
public class GetSellerDesactivateProductsUseCase implements IUseCase<GetSellerDesactivateProductsUseCase.Input, GetSellerDesactivateProductsUseCase.Output> {
    private final ISellerProductsManagerRules sellerProductsManagerRules;
    public GetSellerDesactivateProductsUseCase(ISellerProductsManagerGateway sellerProductsManagerGateway) {
        sellerProductsManagerRules = InstanceLoader.getCoreFactory().getSellerProductsManagerRuleImpl(sellerProductsManagerGateway);
    }

    @Override
    public Output execute(Input input) {
        long sellerId = input.getUsecaseInput().getSellerId();

        var desactivateSellerProducts = sellerProductsManagerRules
                .initialiseRule()
                .getDesactivateProducts(sellerId)
                .getProducts();

        var productsResponse = InstanceLoader.getCoreFactory().getGetAllProductsOutputImpl(
                "La liste de vos produits désactivées",
                desactivateSellerProducts
        );

        return new GetSellerDesactivateProductsUseCase.Output(productsResponse);
    }

    public static class Input extends InputBase<GetDesactivateSellerProductsInput> implements IUseCase.Input {

        public Input(GetDesactivateSellerProductsInput inputPort) {
            super(inputPort);
        }
    }
    public static class Output extends OutputBase<IGetAllProductsOutput> implements IUseCase.Output {

        public Output(IGetAllProductsOutput outputBoundary) {
            super(outputBoundary);
        }
    }
}
