package com.ctoutweb.monsuivi.infra.dto;

public record FilterSellerProductsDto(
        String filterByName,
        String filterByCategoryCode,
        Short filterByRegisterPeriod,
        boolean areSoldProductVisible
) {
}
