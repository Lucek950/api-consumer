package com.example.apiconsumer.exception;

public class NotFoundUserException extends RuntimeException {

  public NotFoundUserException(String username) {
    super(String.format("Not found user: %s", username));
  }
}
