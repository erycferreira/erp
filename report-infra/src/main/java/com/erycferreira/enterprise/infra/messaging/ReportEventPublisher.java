package com.erycferreira.enterprise.infra.messaging;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.erycferreira.enterprise.application.port.ReportEventPort;

@Component
public class ReportEventPublisher implements ReportEventPort {

  private final RabbitTemplate rabbitTemplate;

  public ReportEventPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void reportCreated(UUID id) {
    rabbitTemplate.convertAndSend("reports.exchange", "report.created", id.toString());
  }
}