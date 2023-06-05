package com.example.apiconsumer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseRepository {

  private String nameRepository;
  private String login;
  private List<ResponseBranch> branches;
}
