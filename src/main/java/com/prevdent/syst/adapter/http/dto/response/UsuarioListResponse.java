package com.prevdent.syst.adapter.http.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListResponse {

    private String idUsuario;


    private String nome;


    private String cpf;


    private String dataNascimento;


}
