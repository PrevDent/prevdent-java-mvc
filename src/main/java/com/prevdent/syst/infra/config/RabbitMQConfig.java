package com.prevdent.syst.infra.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

   public static final String QUEUE_CADASTRO_CONSULTA = "filaCadastroConsulta";

   @Bean
   public Jackson2JsonMessageConverter messageConverter() {
      return new Jackson2JsonMessageConverter();
   }

   @Bean
   public RabbitTemplate rabbitTemplate(
           ConnectionFactory connectionFactory,
           MessageConverter messageConverter
   ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;

   }

   @Bean
   public Queue queueCadastroConsulta() {
      return new Queue(QUEUE_CADASTRO_CONSULTA, true);
   }
}
