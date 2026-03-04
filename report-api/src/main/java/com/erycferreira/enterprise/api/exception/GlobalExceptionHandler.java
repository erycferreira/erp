package com.erycferreira.enterprise.api.exception;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.erycferreira.enterprise.domain.exception.BaseBusinessException;
import com.erycferreira.enterprise.domain.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseBusinessException.class)
  public ResponseEntity<ProblemDetail> handleBusiness(
      BaseBusinessException ex,
      HttpServletRequest request) {

    HttpStatus status = ErrorCodeHttpStatusMapper.map(ex.getErrorCode());

    ProblemDetail problem = ProblemDetail.forStatus(status);

    problem.setTitle(status.getReasonPhrase());
    problem.setDetail(ex.getMessage());
    problem.setInstance(URI.create(request.getRequestURI()));

    problem.setProperty("errorCode", ex.getErrorCode());
    problem.setProperty("correlationId", UUID.randomUUID().toString());

    return ResponseEntity.status(status).body(problem);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ProblemDetail> handleNoResource(
      NoResourceFoundException ex,
      HttpServletRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;

    ProblemDetail problem = ProblemDetail.forStatus(status);

    problem.setTitle("Not found");
    problem.setDetail("Route not found");
    problem.setInstance(URI.create(request.getRequestURI()));

    problem.setProperty("errorCode", ErrorCode.ROUTE_NOT_FOUND.name());
    problem.setProperty("correlationId", UUID.randomUUID().toString());

    return ResponseEntity.status(status).body(problem);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleGeneric(
      Exception ex,
      HttpServletRequest request) {

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    ProblemDetail problem = ProblemDetail.forStatus(status);

    problem.setTitle("Internal Server Error");
    problem.setDetail("Unexpected error");
    problem.setInstance(URI.create(request.getRequestURI()));

    problem.setProperty("errorCode", ErrorCode.INTERNAL_ERROR.name());
    problem.setProperty("correlationId", UUID.randomUUID().toString());

    return ResponseEntity.status(status).body(problem);
  }
}
