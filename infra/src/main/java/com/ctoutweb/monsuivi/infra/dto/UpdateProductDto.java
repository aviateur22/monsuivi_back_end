package com.ctoutweb.monsuivi.infra.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record UpdateProductDto(
        @NotNull(message = "L'identifiant du produit est obligatoire")
        Long productId,
        @NotNull(message = "L'identifiant du du vendeur est obligatoire")
        Long sellerId,
        @NotNull(message = "Le prix de vente est obligatoire")
        double productPurchasePrice,
        double productSoldPrice,
        @NotNull(message = "Le date de vente est obligatoire")
        LocalDate productSoldDate
) {
}
