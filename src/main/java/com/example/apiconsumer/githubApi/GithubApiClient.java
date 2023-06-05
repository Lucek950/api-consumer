package com.example.apiconsumer.githubApi;

import com.example.apiconsumer.githubApi.response.GithubBranchResponse;
import com.example.apiconsumer.githubApi.response.GithubRepositoryResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubApiClient {

  @Value("${github.api.version}")
  private String apiVersion;

  @Value("${github.api.user.repositories.url}")
  private String userRepositoriesUrl;

  @Value("${github.api.user.branches.url}")
  private String userBranchesUrl;

  private final RestTemplate restTemplate;

  public GithubApiClient() {
    this.restTemplate = new RestTemplate();
  }

  public List<GithubRepositoryResponse> getUserRepositories(String username) {
    return List.of(
        fetchGithubApiData(
            getUserRepositoriesUrl(username),
            GithubRepositoryResponse[].class
        )
    );
  }

  public List<GithubBranchResponse> getUserBranches(String username, String nameRepository) {
    return List.of(
        fetchGithubApiData(
            getUserBranchesUrl(username, nameRepository),
            GithubBranchResponse[].class
        )
    );
  }

  private <T> T fetchGithubApiData(String url, Class<T> responseType) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-GitHub-Api-Version", apiVersion);
    ResponseEntity<T> responseEntity = restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(headers),
        responseType
    );
    return responseEntity.getBody();
  }

  private String getUserRepositoriesUrl(String username) {
    return String.format(userRepositoriesUrl, username);
  }

  private String getUserBranchesUrl(String username, String repositoryName) {
    return String.format(userBranchesUrl, username, repositoryName);
  }
}
