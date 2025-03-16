package com.prevdent.syst.adapter.repository.mapper;

import com.prevdent.syst.adapter.repository.entity.UsuarioEntity;
import com.prevdent.syst.domain.model.Usuario;
import com.prevdent.syst.domain.user.UsuarioRole;
import com.prevdent.syst.infra.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private SecurityConfiguration securityConfig;

    public Usuario converterUsuario(UsuarioEntity usuarioEntity) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioEntity.getNome());
        usuario.setCpf(usuarioEntity.getCpf());
        usuario.setDataNascimento(usuarioEntity.getDataNascimento());
        usuario.setIdUsuario(usuarioEntity.getIdUsuario());
        return usuario;
    }

    public UsuarioEntity converterUsuarioEntity(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setCpf(usuario.getCpf());
        usuarioEntity.setSenha(securityConfig.passwordEncoder().encode(usuario.getSenha()));
        usuarioEntity.setDataNascimento(usuario.getDataNascimento());
        usuarioEntity.setRole(UsuarioRole.USER_ROLE);
        usuarioEntity.setIdUsuario(usuario.getIdUsuario());
        return usuarioEntity;
    }

    public Usuario converterAtualizacaoDoUsuario(UsuarioEntity usuarioEntity) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioEntity.getNome());
        return usuario;
    }

    public void atualizarUsuarioEntity(UsuarioEntity usuarioEntity, Usuario usuario) {
        usuarioEntity.setNome(usuario.getNome());

        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuarioEntity.setSenha(securityConfig.passwordEncoder().encode(usuario.getSenha()));
        }
    }
}

