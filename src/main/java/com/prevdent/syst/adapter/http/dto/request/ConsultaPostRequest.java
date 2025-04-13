package com.prevdent.syst.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prevdent.syst.domain.model.Dentista;
import com.prevdent.syst.domain.model.Diagnostico;
import com.prevdent.syst.domain.model.Paciente;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ConsultaPostRequest {

    @JsonProperty("id_consulta")
    private String idConsulta;

    @JsonProperty("paciente")
    private PacientePostRequest paciente;

    @JsonProperty("dentista")
    private DentistaPostRequest dentista;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonProperty("data_consulta")
    private Date data;

    @JsonProperty("tipo_tratamento")
    private String tipoTratamento;

    @JsonProperty("diagnostico")
    private Diagnostico diagnostico;

}
