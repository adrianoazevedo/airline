package br.com.hrzon.airline.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final int status;
  private final String error;

  public BusinessException(int status, String message) {
    super(message);
    this.status = status;
    this.error = message;
  }

}
