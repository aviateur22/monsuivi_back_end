package com.ctoutweb.monsuivi.infra.dto;

import com.ctoutweb.monsuivi.infra.annotation.customAnnotation.image.ImageNotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record AddProductDto(
        @ImageNotNull(message = "L'image du produit est obligatoire")
        MultipartFile uploadProductImage,

        @NotNull(message = "Le nom du produit est obligatoire")
        @NotBlank(message = "Le nom du produit est obligatoire")
        String productName,

        @NotNull(message = "Le prix d'achat est obligatoire")
        Double productPurchasePrice,

        @NotNull(message = "La catégorie du produit est obligatoire")
        @NotBlank(message = "La catégorie du produit est obligatoire")
        String productCategory,

        @NotNull(message = "L'identifiant du vendeur est manquant")
        Long sellerId
) {

}
