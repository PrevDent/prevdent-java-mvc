package com.prevdent.syst.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class Consulta {

    private String idConsulta;
    private Paciente paciente;
    private Dentista dentista;
    private Date data;
    private String tipoTratamento;
    private Diagnostico diagnostico;

}
