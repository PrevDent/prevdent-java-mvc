package com.prevdent.syst.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioLoginRequest {

    @JsonProperty("cpf")
/*
    @NotBlank(message = "CPF não pode estar vazio")
*/
    private String cpf;

    @JsonProperty("senha")
    /*@NotBlank(message = "Senha não pode estar vazia")*/
    private String senha;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}

