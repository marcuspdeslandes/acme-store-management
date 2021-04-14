package com.acme.store.persist.domain.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.ServletException;
import java.net.BindException;
import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class ExceptionTranslator {

  @ExceptionHandler(RestClientResponseException.class)
  public ResponseEntity<String> processRestTemplateError(RestClientResponseException ex) {
    log.error("Some error occurred to perform the operation", ex);
    return ResponseEntity.status(Objects.requireNonNull(HttpStatus.resolve(ex.getRawStatusCode())))
        .body(ex.getMessage());
  }

  @ExceptionHandler(RestClientException.class)
  public ResponseEntity<String> processError(RestClientException ex) {
    log.error("A bad request occurred to perform the operation", ex);
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(InvalidParameterException.class)
  public ResponseEntity<String> processError(InvalidParameterException ex) {
    log.error("Invalid parameter.", ex);
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> processBusinessException(BusinessException ex) {
    log.error("An internal server error occurred to perform the operation.", ex);
    if (Optional.ofNullable(ex.getStatus()).isPresent()) {
      return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
  }

  @ExceptionHandler(ServletException.class)
  public ResponseEntity<String> processServletException(ServletException ex) {
    log.error("An internal server error occurred to perform the operation", ex);
    if (Optional.ofNullable(ex.getMessage()).isPresent()
        && HttpStatus.UNAUTHORIZED.name().equals(ex.getMessage())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<String> proccessBusinessException(HttpClientErrorException ex) {
    log.error("A HTTP Client error occurred to perform the operation", ex);
    return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
  }

  @ExceptionHandler(FeignException.class)
  public ResponseEntity<String> processFeignClientError(FeignException ex) {
    log.error("A Feign Client error occurred to perform the operation.", ex);
    return ResponseEntity.status(Objects.requireNonNull(HttpStatus.resolve(ex.status())))
        .body(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> processError(Exception ex) {
    log.error("An internal server error occurred to perform the operation", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<String> processError(BindException ex) {
    log.error("An bad request occurred to perform the operation.", ex);
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> processError(MethodArgumentNotValidException ex) {
    log.error("An bad request occurred to perform the operation.", ex);
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
