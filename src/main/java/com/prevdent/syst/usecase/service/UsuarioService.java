package com.prevdent.syst.usecase.service;

import com.prevdent.syst.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    void cadastrarUsuario(Usuario usuario);

    List<Usuario> listarUsuario();

    Optional<Usuario> atualizarUsuario(String id, Usuario usuario);

    boolean excluirUsuairo(String id);

    Usuario buscarUsuario(String id);

    String validarLogin(String cpf, String senhaDigitada);
}
