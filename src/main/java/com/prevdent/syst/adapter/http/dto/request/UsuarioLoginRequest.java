package com.prevdent.syst.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UsuarioLoginRequest {

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;


}

