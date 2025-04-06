package com.prevdent.syst.adapter;


import com.prevdent.syst.adapter.http.dto.mapper.UsuarioDtoMapper;
import com.prevdent.syst.adapter.http.dto.request.UsuarioCreateRequest;
import com.prevdent.syst.adapter.http.dto.request.UsuarioLoginRequest;
import com.prevdent.syst.adapter.repository.PrevDentFeignClient;

import com.prevdent.syst.domain.model.Consulta;
import com.prevdent.syst.domain.model.Usuario;
import com.prevdent.syst.usecase.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private PrevDentFeignClient prevDentFeignClient;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDtoMapper usuarioDtoMapper;

    private static final String ERRO_CADASTRO_USUARIO = "Erro ao cadastrar usuário";

    private static final String ERRO_CREDENCIAIS = "Credenciais inválidas";



    @GetMapping("/")
    public String redirectToCadastro() {

        log.info("Iniciando o controller de usuário");

        return "redirect:/usuario/cadastro";
    }



    @GetMapping("/cadastro")
    public ModelAndView telaCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("usuarioDto", new UsuarioCreateRequest());
        return modelAndView;
    }


    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(@ModelAttribute UsuarioCreateRequest usuarioDto) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Usuario usuario = usuarioDtoMapper.converterUsuarioDto(usuarioDto);

            usuarioService.cadastrarUsuario(usuario);

            log.info("Usuário cadastrado com sucesso: {}", usuario.getCpf());

            modelAndView.setViewName("redirect:/usuario/login");
        } catch (Exception e) {

            log.error("Erro ao cadastrar usuário: {}", ERRO_CADASTRO_USUARIO);

            modelAndView.addObject("erro", ERRO_CADASTRO_USUARIO);

            modelAndView.setViewName("cadastro");
        }

        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView login(HttpSession session) {

        if (session.getAttribute("token") != null) {
            return new ModelAndView("redirect:/usuario/home");
        }

        ModelAndView mv = new ModelAndView("login");

        mv.addObject("usuarioLoginRequest", new UsuarioLoginRequest());

        return mv;
    }




    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UsuarioLoginRequest usuarioLoginRequest, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            String token = usuarioService.validarLogin(usuarioLoginRequest.getCpf(), usuarioLoginRequest.getSenha());

            session.setAttribute("token", token);
            /*modelAndView.addObject("token", token);*/

            log.info("Usuário logado com sucesso: {}", usuarioLoginRequest.getCpf());

            modelAndView.setViewName("redirect:/usuario/home");
        } catch (Exception e) {
            log.error("Erro ao logar usuário: {}", ERRO_CREDENCIAIS);

            modelAndView.addObject("erro", ERRO_CREDENCIAIS);

            modelAndView.setViewName("login");
        }

        return modelAndView;
    }


    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {

        if (session.getAttribute("token") == null) {
            log.warn("Tentativa de acesso à home sem autenticação");

            return new ModelAndView("redirect:/usuario/login");

        }
        log.info("Acessando a home do usuário com CPF: {}", session.getAttribute("cpf"));
        ModelAndView mv = new ModelAndView("home");


        return mv;
    }

    @GetMapping("/consultas")
    public ModelAndView telaConsultas() {
        List<Consulta> consultas = prevDentFeignClient.listarConsultas();

        log.info("Consultas retornadas com sucesso: {}", consultas);

        ModelAndView mv = new ModelAndView("consultas");

        mv.addObject("consultas", consultas);

        return mv;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/usuario/login";
    }

}
