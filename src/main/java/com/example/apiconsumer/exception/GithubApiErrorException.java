package com.example.apiconsumer.exception;

public class GithubApiErrorException extends RuntimeException {

  public GithubApiErrorException(String message) {
    super("Unable to read Github API error: " + message);
  }
}
