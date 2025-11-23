package com.ctoutweb.monsuivi.infra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull(message = "L'email est obligatoir")
        @NotBlank(message = "L'email est obligatoir")
        @Email(message = "Le format de votre email n'est pas valide")
        String email,
        @NotNull(message = "Le mot de passe est obligatoire")
        @NotBlank(message = "Le mot de passe est obligatoire")
        String password) {
}
