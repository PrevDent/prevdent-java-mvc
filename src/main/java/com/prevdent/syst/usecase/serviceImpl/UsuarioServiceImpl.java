package com.prevdent.syst.usecase.serviceImpl;

import com.prevdent.syst.adapter.repository.entity.UsuarioEntity;
import com.prevdent.syst.adapter.repository.mapper.UsuarioMapper;
import com.prevdent.syst.domain.model.Usuario;
import com.prevdent.syst.infra.security.SecurityConfiguration;
import com.prevdent.syst.infra.security.TokenService;
import com.prevdent.syst.usecase.ports.out.UsuarioPortOut;
import com.prevdent.syst.usecase.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioPortOut usuarioPortOut;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private SecurityConfiguration securityConfig;

    @Autowired
    private TokenService tokenService;

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        if (usuarioPortOut.findByCpf(usuario.getCpf()) != null) {
            throw new RuntimeException("CPF do Usuario já cadastrado");
        }

        UsuarioEntity usuarioEntity = usuarioMapper.converterUsuarioEntity(usuario);

        usuarioPortOut.save(usuarioEntity);
    }

    @Override
    public List<Usuario> listarUsuario() {
        List<UsuarioEntity> usuariosExistentes = usuarioPortOut.findAll();

        List<Usuario> usuarios = new ArrayList<>();

        for (UsuarioEntity usuarioEntity : usuariosExistentes) {

            Usuario usuario = usuarioMapper.converterUsuario(usuarioEntity);

            usuarios.add(usuario);
        }

        return usuarios;
    }

    @Override
    public Optional<Usuario> atualizarUsuario(String id, Usuario usuario) {
        Optional<UsuarioEntity> usuarioExistente = usuarioPortOut.findById(id);

        if (usuarioExistente.isPresent()) {

            UsuarioEntity atualizar = usuarioExistente.get();

            usuarioMapper.atualizarUsuarioEntity(atualizar, usuario);

            UsuarioEntity usuarioAtualizado = usuarioPortOut.save(atualizar);

            return Optional.of(usuarioMapper.converterAtualizacaoDoUsuario(usuarioAtualizado));

        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean excluirUsuairo(String id) {

        Optional<UsuarioEntity> usuarioExistente = usuarioPortOut.findById(id);

        if (usuarioExistente.isPresent()) {

            usuarioPortOut.deleteById(id);

            return true;

        } else {
            return false;
        }
    }

    @Override
    public Usuario buscarUsuario(String id) {
        Optional<UsuarioEntity> usuarioDetalhado = usuarioPortOut.findById(id);

        if (usuarioDetalhado.isPresent()) {
            return usuarioMapper.converterUsuario(usuarioDetalhado.get());
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    @Override
    public String validarLogin(String cpf, String senhaDigitada) {
        UsuarioEntity usuarioEntity = usuarioPortOut.findByCpf(cpf);

        if (usuarioEntity == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!securityConfig.passwordEncoder().matches(senhaDigitada, usuarioEntity.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        return tokenService.gerarToken(usuarioEntity);
    }

}
