package com.prevdent.syst.adapter;

import com.prevdent.syst.adapter.http.dto.request.ConsultaPostRequest;
import com.prevdent.syst.adapter.repository.PrevDentFeignClient;
import com.prevdent.syst.domain.model.Consulta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/usuario/consulta")
public class ConsultaController {

    @Autowired
    private PrevDentFeignClient prevDentFeignClient;


    @GetMapping("/formulario")
    public String formularioConsulta(Model model) {

        model.addAttribute("consulta", new Consulta());

        return "formularioConsulta";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@ModelAttribute ConsultaPostRequest consulta, Model model) {
        try {
            log.info("Iniciando o cadastro da consulta: {}", consulta);

            prevDentFeignClient.cadastrarConsulta(consulta);

            log.info("Consulta cadastrada com sucesso: {}", consulta);

            return "redirect:/usuario/consultas";
        } catch (Exception e) {

            log.error("Erro ao cadastrar a consulta: {}", consulta, e);

            model.addAttribute("mensagemErro", "Ocorreu um erro ao tentar cadastrar a consulta.");
            model.addAttribute("consulta", consulta);

            return "formularioConsulta";
        }
    }
}

