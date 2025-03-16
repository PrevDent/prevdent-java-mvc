package com.prevdent.syst.adapter;


import com.prevdent.syst.adapter.http.dto.mapper.UsuarioDtoMapper;
import com.prevdent.syst.adapter.http.dto.request.UsuarioCreateRequest;
import com.prevdent.syst.adapter.http.dto.request.UsuarioLoginRequest;
import com.prevdent.syst.domain.model.Usuario;
import com.prevdent.syst.usecase.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {



    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDtoMapper usuarioDtoMapper;


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

            System.out.println("Usuário cadastrado: " + usuario.getCpf());

            modelAndView.setViewName("redirect:/usuario/login");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            modelAndView.addObject("erro", "Erro ao cadastrar usuário.");
            modelAndView.setViewName("cadastro");
        }

        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("usuarioLoginRequest", new UsuarioLoginRequest());
        return mv;
    }




    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UsuarioLoginRequest usuarioLoginRequest) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            String token = usuarioService.validarLogin(usuarioLoginRequest.getCpf(), usuarioLoginRequest.getSenha());
            modelAndView.addObject("token", token);
            modelAndView.setViewName("home"); // Nome da página para onde redirecionar após login bem-sucedido
        } catch (Exception e) {
            modelAndView.addObject("erro", "Credenciais inválidas");
            modelAndView.setViewName("login"); // Retorna para a página de login com a mensagem de erro
        }

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

}
