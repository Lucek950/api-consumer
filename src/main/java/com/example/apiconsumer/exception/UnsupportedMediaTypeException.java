package com.example.apiconsumer.exception;

public class UnsupportedMediaTypeException extends RuntimeException {

  public UnsupportedMediaTypeException(String mediaTypeValue) {
    super(String.format("Unsupported media type: %s", mediaTypeValue));
  }
}
