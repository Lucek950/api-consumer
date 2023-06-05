package com.example.apiconsumer.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class GetRepositoriesIT {

  @Autowired
  private MockMvc mockMvc;

  private final static String REPOSITORY_PATH = "/repository";

  @Test
  void should_not_found_when_get_repository_by_username() throws Exception {
    mockMvc.perform(
            get(REPOSITORY_PATH + "/" + UUID.randomUUID())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        )
        .andExpect(
            status().is(HttpStatus.NOT_FOUND.value())
        )
        .andExpect(
            content().json("""
                        {
                            "statusCode": 404,
                            "message": "Not Found"
                        }
                """, true)
        );
  }
}
