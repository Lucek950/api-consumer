package com.example.apiconsumer.githubApi.response;

public record GithubRepositoryResponse(
    String name,
    GithubOwnerResponse owner,
    boolean fork
) {

  public record GithubOwnerResponse(String login) {

  }
}
