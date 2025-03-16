package com.prevdent.syst.domain.model;

import com.prevdent.syst.domain.user.UsuarioRole;
import lombok.Data;

import java.util.UUID;

@Data
public class Usuario {
    private String idUsuario;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String senha;
    private UsuarioRole role;

    public Usuario() {
        this.idUsuario = UUID.randomUUID().toString();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }


}

