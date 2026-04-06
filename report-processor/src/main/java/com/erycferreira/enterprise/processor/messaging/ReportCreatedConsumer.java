package com.erycferreira.enterprise.processor.messaging;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.erycferreira.enterprise.application.usecase.ProcessReportUseCase;
import com.erycferreira.enterprise.processor.config.RabbitConfig;

@Component
public class ReportCreatedConsumer {

  private static final Logger log = LoggerFactory.getLogger(ReportCreatedConsumer.class);

  private final ProcessReportUseCase processReportUseCase;

  public ReportCreatedConsumer(ProcessReportUseCase processReportUseCase) {
    this.processReportUseCase = processReportUseCase;
  }

  @RabbitListener(queues = RabbitConfig.QUEUE)
  public void consume(String reportId) {
    log.info("Received report processing request reportId={}", reportId);
    processReportUseCase.execute(UUID.fromString(reportId));
  }
}
