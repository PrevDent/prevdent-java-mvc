package com.prevdent.syst.adapter.http.dto.mapper;

import com.prevdent.syst.adapter.http.dto.request.UsuarioCreateRequest;
import com.prevdent.syst.adapter.http.dto.request.UsuarioLoginRequest;
import com.prevdent.syst.adapter.http.dto.request.UsuarioUpdateRequest;
import com.prevdent.syst.adapter.http.dto.response.UsuarioListResponse;
import com.prevdent.syst.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoMapper {

    public Usuario converterUsuarioDto(UsuarioCreateRequest usuarioCreateRequest) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCreateRequest.getNome());
        usuario.setCpf(usuarioCreateRequest.getCpf());
        usuario.setDataNascimento(usuarioCreateRequest.getDataNascimento());
        usuario.setSenha(usuarioCreateRequest.getSenha());
        usuario.setRole(usuarioCreateRequest.getRole());
        return usuario;
    }

    public Usuario converterUsuarioUpdateDto(UsuarioUpdateRequest usuarioUpdateRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioUpdateRequest.getNome());
        usuario.setSenha(usuarioUpdateRequest.getSenha());
        return usuario;
    }

    public UsuarioListResponse converterUsuarioListaResponse(Usuario usuario) {
        UsuarioListResponse response = new UsuarioListResponse();
        response.setIdUsuario(usuario.getIdUsuario());
        response.setNome(usuario.getNome());
        response.setDataNascimento(usuario.getDataNascimento());
        return response;
    }

    public Usuario converterUsuarioLoginDto(UsuarioLoginRequest usuarioLoginRequest) {
        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioLoginRequest.getCpf());
        usuario.setSenha(usuarioLoginRequest.getSenha());
        return usuario;
    }
}

