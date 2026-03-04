package com.erycferreira.enterprise.api.exception;

import org.springframework.http.HttpStatus;

import com.erycferreira.enterprise.domain.exception.ErrorCode;

public class ErrorCodeHttpStatusMapper {
  public static HttpStatus map(ErrorCode errorcode) {
    switch (errorcode) {
      case REPORT_NOT_FOUND:
        return HttpStatus.NOT_FOUND;
      case REPORT_ALREADY_PROCESSING:
        return HttpStatus.BAD_REQUEST;
      case ROUTE_NOT_FOUND:
        return HttpStatus.NOT_FOUND;

      default:
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }
}
