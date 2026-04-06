package com.erycferreira.enterprise.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.erycferreira.enterprise.processor",
    "com.erycferreira.enterprise.infra"
})
@EnableJpaRepositories(basePackages = "com.erycferreira.enterprise.infra.repository")
@EntityScan(basePackages = "com.erycferreira.enterprise.infra.entity")
public class ReportProcessorApplication {
  public static void main(String[] args) {
    SpringApplication.run(ReportProcessorApplication.class, args);
  }
}
