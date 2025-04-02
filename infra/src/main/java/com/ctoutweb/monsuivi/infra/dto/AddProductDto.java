package com.ctoutweb.monsuivi.infra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record AddProductDto(
        MultipartFile uploadProductImage,

        @NotNull(message = "Le nom du produit est obligatoire")
        @NotBlank(message = "Le nom du produit est obligatoire")
        String productName,

        @NotNull(message = "Le prix d'achat est obligatoire")
        double productPurchasePrice,

        @NotNull(message = "Le prix de vente souhaité est obligatoire")
        double productDesiredSoldPrice,

        @NotNull(message = "La catégorie du produit est obligatoire")
        @NotBlank(message = "La catégorie du produit est obligatoire")
        String productCategory,

        @NotNull(message = "L'identifiant du vendeur est manquant")
        long sellerId
) {

}
