package com.guisebastiao.api.controllers.dtos;

import com.guisebastiao.api.validation.ValidNumberPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserUpdateDTO(
        @NotBlank(message = "Informe seu nome")
        @Size(min = 3, message = "O nome deve ter mais de 3 caracteres")
        @Size(max = 50, message = "O nome deve ter menos de 50 caracteres")
        String name,

        @NotBlank(message = "Informe seu cpf")
        @CPF(message = "Informe um cpf válido")
        String cpf,

        @NotBlank(message = "Informe seu número de telefone")
        @ValidNumberPhone(message = "Informe um número de telefone válido")
        String phone
){
}
