package com.example.apiconsumer;

import com.example.apiconsumer.exception.UnsupportedMediaTypeException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-consumer")
public class Controller {

  private final Servicee service;

  @GetMapping("get-repository/{username}")
  public ResponseEntity<List<ResponseRepository>> getGitHubUserRepository(
      @PathVariable String username,
      @RequestHeader String header
  ) {
    if (!header.equals(MediaType.APPLICATION_JSON_VALUE)) {
      throw new UnsupportedMediaTypeException(header);
    }

    var result = service.getResponseRepository(username);

    return ResponseEntity.ok(result);
  }
}
