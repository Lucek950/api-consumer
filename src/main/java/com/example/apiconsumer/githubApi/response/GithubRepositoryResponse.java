package com.example.apiconsumer.githubApi.response;

public record GithubRepositoryResponse(
    String name,
    GithubOwnerResponse owner
) {

  public record GithubOwnerResponse(String login) {

  }
}
