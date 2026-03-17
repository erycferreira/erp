package com.erycferreira.enterprise.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.erycferreira.enterprise.domain.exception.ReportAlreadyProcessingException;

class ReportTest {

  @Test
  void shouldCreateReportWithStatusCreated() {
    Report report = new Report("Monthly Sales", ReportType.SALES);

    assertEquals(ReportStatus.CREATED, report.getStatus());
    assertNotNull(report.getId());
    assertNotNull(report.getCreatedAt());
  }

  @Test
  void shouldTransitionToProcessingFromCreated() {
    Report report = new Report("Monthly Sales", ReportType.SALES);

    report.startProcessing();

    assertEquals(ReportStatus.PROCESSING, report.getStatus());
  }

  @Test
  void shouldThrowWhenStartProcessingFromNonCreatedStatus() {
    Report report = new Report("Monthly Sales", ReportType.SALES);
    report.startProcessing();

    assertThrows(ReportAlreadyProcessingException.class, report::startProcessing);
  }

  @Test
  void shouldTransitionToCompletedFromProcessing() {
    Report report = new Report("Monthly Sales", ReportType.SALES);
    report.startProcessing();

    report.complete();

    assertEquals(ReportStatus.COMPLETED, report.getStatus());
  }

  @Test
  void shouldTransitionToFailedFromProcessing() {
    Report report = new Report("Monthly Sales", ReportType.SALES);
    report.startProcessing();

    report.fail();

    assertEquals(ReportStatus.FAILED, report.getStatus());
  }

  @Test
  void shouldThrowWhenCompletingFromCreatedStatus() {
    Report report = new Report("Monthly Sales", ReportType.SALES);

    assertThrows(ReportAlreadyProcessingException.class, report::complete);
  }
}
