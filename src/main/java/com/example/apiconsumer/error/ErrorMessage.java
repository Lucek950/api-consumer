package com.example.apiconsumer.error;

public record ErrorMessage(
    int statusCode,
    String message
) {

}
