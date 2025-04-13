package com.prevdent.syst.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Consulta {

    private String idConsulta;

    private Paciente paciente;

    private Dentista dentista;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date data;

    private String tipoTratamento;

    private Diagnostico diagnostico;

}
