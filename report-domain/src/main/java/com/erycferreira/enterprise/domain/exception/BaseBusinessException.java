package com.erycferreira.enterprise.domain.exception;

public abstract class BaseBusinessException extends RuntimeException {

  private ErrorCode errorCode;

  protected BaseBusinessException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return this.errorCode;
  }
}
