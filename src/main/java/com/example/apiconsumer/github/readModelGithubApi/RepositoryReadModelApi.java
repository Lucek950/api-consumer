package com.example.apiconsumer.github.readModelGithubApi;

public record RepositoryReadModelApi(
    Owner owner,
    String name
) {

  public record Owner(String login) {

  }
}
