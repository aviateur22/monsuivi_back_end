package com.ctoutweb.monsuivi.infra.dto;

import com.ctoutweb.monsuivi.infra.annotation.customAnnotation.localDate.IsValidLocalDate;
import com.ctoutweb.monsuivi.infra.annotation.customAnnotation.productStatus.isProductStatusValid;
import jakarta.validation.constraints.NotNull;

public record UpdateProductDto(
        @NotNull(message = "L'identifiant du produit est obligatoire")
        Long productId,
        @NotNull(message = "L'identifiant du du vendeur est obligatoire")
        Long sellerId,
        @NotNull(message = "Le prix de vente est obligatoire")
        double productPurchasePrice,
        double productSoldPrice,
        @NotNull(message = "Le date de vente est obligatoire")
        @IsValidLocalDate(message = "Le format de la date d'achat n'est pas correcte")
        String productBuyDay,
        @IsValidLocalDate(message = "Le format de la date de vente n'est pas correcte")
        String productSoldDay,

        @NotNull(message = "Le statut du produit est obligatoire")
        @isProductStatusValid(message = "Le statut du produit n'est pas valide")
        String productStatus
) {
}
