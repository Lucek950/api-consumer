package com.example.apiconsumer.validator;

import com.example.apiconsumer.exception.UnsupportedMediaTypeException;
import org.springframework.http.MediaType;

public class ControllerHeaderValidator {

  public static void validateMediaType(String header) {
    if (!header.equals(MediaType.APPLICATION_JSON_VALUE)
        && !header.equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
      throw new UnsupportedMediaTypeException(header);
    }
  }
}
