package com.prevdent.syst.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prevdent.syst.domain.user.UsuarioRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateRequest {

    private String idUsuario;

    @NotBlank
    private String nome;


    @NotBlank(message = "CPF não pode estar vazio")
    @CPF(message = "CPF inválido")
    private String cpf;


    @NotNull
    private String dataNascimento;

    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
    )
    private String senha;

    private UsuarioRole role;



}

