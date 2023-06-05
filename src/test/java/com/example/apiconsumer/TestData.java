package com.example.apiconsumer;

import com.example.apiconsumer.githubApi.response.GithubRepositoryResponse;
import com.example.apiconsumer.githubApi.response.GithubRepositoryResponse.GithubOwnerResponse;
import java.util.List;

public class TestData {

  public static String getTestUsername() {
    return "TestUser";
  }

  public static GithubRepositoryResponse createGithubRepository(
      String name,
      boolean fork
  ) {
    return new GithubRepositoryResponse(
        name,
        new GithubOwnerResponse(getTestUsername()),
        fork
    );
  }

  public static List<GithubRepositoryResponse> createGithubRepositories() {
    return List.of(
        createGithubRepository("repo1", false),
        createGithubRepository("repo2", false)
    );
  }

  public static List<GithubRepositoryResponse> createForksGithubRepositories() {
    return List.of(
        createGithubRepository("repo1", true),
        createGithubRepository("repo2", true)
    );
  }

}
