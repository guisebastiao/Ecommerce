package com.guisebastiao.api.controllers.dtos;

import com.guisebastiao.api.validation.ValidNumberPhone;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record RegisterDTO(
        @NotBlank(message = "Informe seu nome")
        @Size(min = 3, message = "O nome deve ter mais de 3 caracteres")
        @Size(max = 50, message = "O nome deve ter menos de 50 caracteres")
        String name,

        @NotBlank(message = "Informe seu email")
        @Email(message = "Informe um email válido")
        @Size(max = 255, message = "O email deve ter menos de 255 caracteres")
        String email,

        @NotBlank(message = "Informe sua senha")
        @Size(min = 6, message = "A senha deve ter mais de 6 caracteres")
        @Size(max = 20, message = "A senha deve ter menos de 20 caracteres")
        @Pattern.List({
                @Pattern(regexp = ".*[A-Z].*", message = "A senha deve ter uma letra maiúscula"),
                @Pattern(regexp = ".*\\d.*\\d.*", message = "A senha deve ter dois números"),
                @Pattern(regexp = ".*[@$!%*?&.#].*", message = "A senha deve ter um caractere especial")
        })
        String password,

        @NotBlank(message = "Informe seu cpf")
        @CPF(message = "Informe um cpf válido")
        String cpf,

        @NotBlank(message = "Informe seu número de telefone")
        @ValidNumberPhone(message = "Informe um número de telefone válido")
        String phone
) {
}
