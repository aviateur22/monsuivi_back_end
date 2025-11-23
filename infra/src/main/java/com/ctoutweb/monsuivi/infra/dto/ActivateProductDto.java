package com.ctoutweb.monsuivi.infra.dto;

import jakarta.validation.constraints.NotNull;

public record ActivateProductDto(
    @NotNull(message = "L'identifiant du produit est obligatoire")
    Long productId,
    @NotNull(message = "L'identifiant du vendeur est obligatoire")
    Long sellerId) {
}
