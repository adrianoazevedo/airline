package br.com.hrzon.airline.domain.exception;

import br.com.hrzon.airline.controller.dto.response.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException extends RuntimeException {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> business(BusinessException ex) {
    return new ResponseEntity<>(new ErrorResponse(ex.getStatus(), ex.getMessage()), HttpStatusCode.valueOf(ex.getStatus()));
  }

}
