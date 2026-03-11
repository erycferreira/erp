package com.erycferreira.enterprise.api.exception;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.erycferreira.enterprise.domain.exception.BaseBusinessException;
import com.erycferreira.enterprise.domain.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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

    String correlationId = MDC.get("correlationId");

    log.error(
        "Unexpected error processing request {} {} correlationId={}",
        request.getMethod(),
        request.getRequestURI(),
        correlationId,
        ex);

    ProblemDetail problem = ProblemDetail.forStatus(status);

    problem.setTitle("Internal Server Error");
    problem.setDetail("An unexpected error occurred");
    problem.setInstance(URI.create(request.getRequestURI()));

    problem.setProperty("errorCode", ErrorCode.INTERNAL_ERROR.name());
    problem.setProperty("correlationId", UUID.randomUUID().toString());

    return ResponseEntity.status(status).body(problem);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetail> handleValidation(
      MethodArgumentNotValidException ex,
      HttpServletRequest request) {

    HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .toList();

    log.warn(
        "Validation error on {} {}: {} correlationId={}",
        request.getMethod(),
        request.getRequestURI(),
        errors,
        MDC.get("correlationId"));

    ProblemDetail problem = ProblemDetail.forStatus(status);

    problem.setTitle("Validation Error");
    problem.setDetail("Failure to validate");
    problem.setInstance(URI.create(request.getRequestURI()));

    problem.setProperty("errors", errors);
    problem.setProperty("errorCode", ErrorCode.VALIDATION_ERROR.name());
    problem.setProperty("correlationId", UUID.randomUUID().toString());

    return ResponseEntity.status(status).body(problem);
  }
}
