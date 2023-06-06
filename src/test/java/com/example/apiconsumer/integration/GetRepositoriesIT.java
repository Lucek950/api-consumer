package com.example.apiconsumer.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GetRepositoriesIT {

  @Autowired
  private MockMvc mockMvc;

  private final static String REPOSITORY_PATH = "/repository";

  @Test
  void should_not_found_when_get_repository_by_username() throws Exception {
    mockMvc.perform(
            get(REPOSITORY_PATH + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(""))
        .andExpect(status().isNotFound())
        .andExpect(content().json("""
            {
                "statusCode": 404,
                "message": "Not Found, URL: https://docs.github.com/rest/reference/repos#list-repositories-for-a-user"
            }
            """, true));
  }

  @Test
  void should_not_accept_when_given_http_header_application_xml() throws Exception {
    mockMvc.perform(
            get(REPOSITORY_PATH + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_XML)
                .characterEncoding("UTF-8"))
        .andExpect(
            status().is(HttpStatus.NOT_ACCEPTABLE.value())
        )
        .andExpect(
            content().json("""
                        {
                            "statusCode": 406,
                            "message": "Unsupported media type: application/xml;charset=UTF-8"
                        }
                """, true)
        );
  }
}
