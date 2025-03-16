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

    @JsonProperty("id_usuario")
    private String idUsuario;

    @JsonProperty("nome")
    @NotBlank
    private String nome;

    @JsonProperty("cpf")
    @NotBlank(message = "CPF não pode estar vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @JsonProperty("data_nascimento")
    @NotNull
    private String dataNascimento;

    @JsonProperty("senha")
    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
    )
    private String senha;

    private UsuarioRole role;


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

