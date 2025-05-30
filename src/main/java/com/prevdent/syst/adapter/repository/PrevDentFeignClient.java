package com.prevdent.syst.adapter.repository;

import com.prevdent.syst.adapter.http.dto.request.ConsultaPostRequest;
import com.prevdent.syst.adapter.http.dto.request.PerguntaRequest;
import com.prevdent.syst.domain.model.Consulta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "prevdentFeign", url = "${FEIGN_CORE_URL}/consulta")
public interface PrevDentFeignClient {

    @GetMapping
    List<Consulta> listarConsultas();

    @PostMapping("/cadastrar")
    Consulta cadastrarConsulta(@RequestBody ConsultaPostRequest consulta);

    @PostMapping("/ia/pergunta")
    String respoderPergunta(@RequestBody PerguntaRequest pergunta);
}
