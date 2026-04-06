package com.erycferreira.enterprise.processor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  public static final String EXCHANGE = "reports.exchange";
  public static final String QUEUE = "reports.created";
  public static final String KEY = "report.created";

  @Bean
  public DirectExchange reportsExchange() {
    return new DirectExchange(EXCHANGE);
  }

  @Bean
  public Queue reportsCreatedQueue() {
    return new Queue(QUEUE, true);
  }

  @Bean
  public Binding bindind(Queue reportsCreatedQueue, DirectExchange reportsExchange) {
    return BindingBuilder.bind(reportsCreatedQueue).to(reportsExchange).with(KEY);
  }
}
