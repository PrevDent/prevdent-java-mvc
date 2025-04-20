package com.prevdent.syst.adapter;

import com.prevdent.syst.adapter.http.dto.request.ConsultaPostRequest;
import com.prevdent.syst.adapter.repository.PrevDentFeignClient;
import com.prevdent.syst.infra.security.TokenService;
import com.prevdent.syst.usecase.ports.out.UsuarioPortOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(ConsultaController.class)
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrevDentFeignClient prevDentFeignClient;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioPortOut usuarioPortOut;

    @Test
    @DisplayName("Deve retornar a tela de formulário de consulta com o objeto consulta no model")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testFormularioConsulta() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/usuario/consulta/formulario"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("formularioConsulta"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("consulta"));
    }

    @Test
    @DisplayName("Deve salvar a consulta e redirecionar para a lista de consultas")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testSalvarConsultaError() throws Exception {
        Mockito.doThrow(new RuntimeException("Error"))
                .when(prevDentFeignClient).cadastrarConsulta(any(ConsultaPostRequest.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/consulta/salvar")
                        .param("paciente.cpf", "12345678901")
                        .param("dentista.documento", "987654321")
                        .param("dataConsulta", "2025-04-13")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("formularioConsulta"));
    }

    @Test
    @DisplayName("Deve retornar erro 403 ao tentar salvar consulta sem CSRF")
    void testSalvarConsultaErrorWithoutCsrf() throws Exception {
        Mockito.doThrow(new RuntimeException("Error"))
                .when(prevDentFeignClient).cadastrarConsulta(any(ConsultaPostRequest.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/usuario/consulta/salvar")
                        .param("paciente.cpf", "12345678901")
                        .param("dentista.documento", "987654321")
                        .param("data", "2025-04-13T10:00")
                        .param("tipoTratamento", "Limpeza"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}