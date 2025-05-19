package com.prevdent.syst.adapter;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.prevdent.syst.adapter.http.dto.request.MensagemIA;
import com.prevdent.syst.adapter.http.dto.request.PerguntaRequest;
import com.prevdent.syst.adapter.repository.PrevDentFeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuario/chat")
@RequiredArgsConstructor
public class ChatController {

    private final PrevDentFeignClient feignClient;

    private final List<MensagemIA> historico = new ArrayList<>();

    @GetMapping
    public String abrirChat(Model model, HttpSession session) {

    if (session.getAttribute("token") == null) {
            return "redirect:/usuario/login";
        }


        model.addAttribute("mensagens", historico);
        model.addAttribute("perguntaRequest", new PerguntaRequest(""));
        return "chat";
    }

    @PostMapping
    public String enviarPergunta(@ModelAttribute PerguntaRequest perguntaRequest) {
        String resposta = feignClient.respoderPergunta(perguntaRequest);
        historico.add(new MensagemIA(perguntaRequest.pergunta(), resposta));
        return "redirect:/usuario/chat";
    }

}
