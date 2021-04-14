package com.acme.store.job.domain.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
  private HttpStatus status;

  public BusinessException(String message, Throwable e) {
    super(message, e);
  }

  public BusinessException(String message, HttpStatus status, Throwable e) {
    super(message, e);
    this.status = status;
  }

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, HttpStatus httpStatus) {
    super(message);
    this.status = httpStatus;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
