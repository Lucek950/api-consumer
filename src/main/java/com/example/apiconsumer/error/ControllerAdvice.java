package com.example.apiconsumer.error;

import com.example.apiconsumer.exception.NotFoundUserException;
import com.example.apiconsumer.exception.UnsupportedMediaTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler({
      NotFoundUserException.class,
  })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorMessage handleNotFound(Exception exception) {
    return new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        exception.getMessage()
    );
  }

  @ExceptionHandler({
      UnsupportedMediaTypeException.class,
  })
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorMessage handleNotAcceptable(Exception exception) {
    return new ErrorMessage(
        HttpStatus.NOT_ACCEPTABLE.value(),
        exception.getMessage()
    );
  }

  @ExceptionHandler({
      HttpClientErrorException.class,
  })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleInternalServerError(Exception exception) {
    return new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        exception.getMessage()
    );
  }
}
