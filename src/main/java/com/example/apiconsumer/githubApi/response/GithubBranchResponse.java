package com.example.apiconsumer.githubApi.response;

public record GithubBranchResponse(
    String name,
    GithubCommitResponse commit
) {

  public record GithubCommitResponse(String sha) {

  }
}
