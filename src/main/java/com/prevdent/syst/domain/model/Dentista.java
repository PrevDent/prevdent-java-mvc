package com.prevdent.syst.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dentista {

    private String idDentista;
    private String nome;
    private String documento;
    private String especializacao;

    public String getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(String idDentista) {
        this.idDentista = idDentista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

}