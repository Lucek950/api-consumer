package com.example.apiconsumer.exception;

public class UnsupportedMediaTypeException extends RuntimeException {

  public UnsupportedMediaTypeException(String mediaType) {
    super(String.format("Unsupported media type: %s", mediaType));
  }
}
