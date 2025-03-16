package com.prevdent.syst.usecase.ports.out;

import com.prevdent.syst.adapter.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPortOut extends JpaRepository<UsuarioEntity, String> {

    UsuarioEntity findByCpf(String cpf);
}
