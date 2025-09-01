package io.luankuhlmann.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotBlank(message = "Informe seu nome")
        String name,

        @NotBlank(message = "Informe um email")
        @Email(message = "Informe um email válido")
        String email,

        @NotBlank(message = "Informe uma senha")
//        @Pattern(
//                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&()_+=\\-{}\\[\\]:;\"'<>,./\\\\|^~`#])" +
//                        "[A-Za-z\\d@$!%*?&()_+=\\-{}\\[\\]:;\"'<>,./\\\\|^~`#]{8,16}$",
//                message = "A senha deve ter entre 8 e 16 caracteres, incluindo pelo menos uma letra maiúscula, " +
//                        "uma minúscula, um número e um caractere especial."
//        )
        String password
) {
}
