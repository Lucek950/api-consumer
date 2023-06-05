package com.example.apiconsumer.github.readModelGithubApi;

public record BranchReadModelApi(
    String name,
    Commit commit
) {

  public record Commit(String sha) {

  }
}
