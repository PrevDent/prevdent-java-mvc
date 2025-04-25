package com.prevdent.syst.infra.config;


import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@TestConfiguration
public class RabbitMQConfigTest {

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return Mockito.mock(RabbitTemplate.class);
    }

    @Bean
    public Queue queueCadastroConsulta() {
        return new Queue("filaCadastroConsulta");
    }
  
}