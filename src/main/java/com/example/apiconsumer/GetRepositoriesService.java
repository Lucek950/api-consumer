package com.example.apiconsumer;

import com.example.apiconsumer.githubApi.GithubApiClient;
import com.example.apiconsumer.githubApi.response.GithubRepositoryResponse;
import com.example.apiconsumer.response.BranchResponse;
import com.example.apiconsumer.response.RepositoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetRepositoriesService {

  private final GithubApiClient githubApiClient;

  private List<BranchResponse> getBranchesForRepository(GithubRepositoryResponse repository) {
    var githubBranches = githubApiClient.getUserBranches(
        repository.owner().login(),
        repository.name()
    );
    return githubBranches.stream()
        .map(githubBranch -> new BranchResponse(
            githubBranch.name(),
            githubBranch.commit().sha()))
        .toList();
  }

  public List<RepositoryResponse> getRepositoriesForUserLogin(String username) {
    var githubRepositories = githubApiClient.getUserRepositories(username);
    return githubRepositories.stream()
        .filter(githubRepository -> !githubRepository.fork())
        .map(githubRepository -> new RepositoryResponse(
            githubRepository.name(),
            githubRepository.owner().login(),
            getBranchesForRepository(githubRepository)
        ))
        .toList();
  }
}
