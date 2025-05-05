package com.prevdent.syst.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PacientePostRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("data_nascimento")
    private String dataNascimento;


    @JsonProperty("consultas")
    private List<ConsultaPostRequest> consultas;
}
