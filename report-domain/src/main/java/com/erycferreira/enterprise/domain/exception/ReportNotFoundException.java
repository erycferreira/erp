package com.erycferreira.enterprise.domain.exception;

public class ReportNotFoundException extends BaseBusinessException {

  public ReportNotFoundException(String id) {
    super(
        "Report not found: " + id,
        ErrorCode.REPORT_NOT_FOUND);
  }
}
