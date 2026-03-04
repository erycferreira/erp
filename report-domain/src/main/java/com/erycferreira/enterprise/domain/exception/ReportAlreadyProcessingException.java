package com.erycferreira.enterprise.domain.exception;

public class ReportAlreadyProcessingException extends BaseBusinessException {

  public ReportAlreadyProcessingException(String status) {
    super(
        "Report cannot start processing from status: " + status,
        ErrorCode.REPORT_ALREADY_PROCESSING);
  }

}