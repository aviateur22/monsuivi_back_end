package com.ctoutweb.monsuivi.core.entity.product;

import com.ctoutweb.monsuivi.core.exception.CoreException;

import java.util.Arrays;

public enum ProductStatus {
    FOR_SALE("fs"),
    SOLD("so");

    private String code;

    private ProductStatus(String code) {
        this.code = code;
    }

    public String getProductStatusCode() {
        return this.code;
    }

    public static ProductStatus getProductStatus(String statusCode) {
        return Arrays.stream(ProductStatus.values())
                .filter(produtcStatus-> produtcStatus.code.equalsIgnoreCase(statusCode))
                .findFirst()
                .orElseThrow(()-> new CoreException("Le statut du produit n'existe pas"));
    }
}
