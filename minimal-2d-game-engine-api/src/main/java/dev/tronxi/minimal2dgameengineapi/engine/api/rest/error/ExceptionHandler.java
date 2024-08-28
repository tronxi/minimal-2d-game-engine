package dev.tronxi.minimal2dgameengineapi.engine.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Object> exception(RuntimeException exception) {
    return new ResponseEntity<>(new Error(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
