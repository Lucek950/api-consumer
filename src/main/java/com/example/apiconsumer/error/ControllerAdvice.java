package com.example.apiconsumer.error;

import com.example.apiconsumer.exception.GithubApiErrorException;
import com.example.apiconsumer.exception.UnsupportedMediaTypeException;
import com.example.apiconsumer.githubApi.response.GithubErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@RestControllerAdvice
class ControllerAdvice {

  private final ObjectMapper objectMapper;

  @ExceptionHandler({
      GithubApiErrorException.class
  })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleInternalServerError(Exception exception) {
    return new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        exception.getMessage()
    );
  }

  @ExceptionHandler({
      UnsupportedMediaTypeException.class
  })
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorMessage handleNotAcceptable(Exception exception) {
    return new ErrorMessage(
        HttpStatus.NOT_ACCEPTABLE.value(),
        exception.getMessage()
    );
  }

  @ExceptionHandler({
      HttpClientErrorException.class
  })
  public ResponseEntity<ErrorMessage> handleInternalServerError(HttpClientErrorException exception) {
    GithubErrorResponse githubErrorResponse;
    try {
      githubErrorResponse = objectMapper.readValue(exception.getResponseBodyAsString(), GithubErrorResponse.class);
    } catch (JsonProcessingException e) {
      throw new GithubApiErrorException(e.getMessage());
    }
    return new ResponseEntity<>(
        new ErrorMessage(
            exception.getStatusCode().value(),
            githubErrorResponse.message()),
        exception.getStatusCode()
    );
  }
}
