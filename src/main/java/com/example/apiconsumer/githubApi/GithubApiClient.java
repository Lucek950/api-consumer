package com.example.apiconsumer.githubApi;

import com.example.apiconsumer.githubApi.response.GithubBranchResponse;
import com.example.apiconsumer.githubApi.response.GithubRepositoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class GithubApiClient {

  private static final String API_VERSION = "2022-11-28";
  private static final String GITHUB_API = "https://api.github.com";
  private static final String USER_REPOSITORIES_API = GITHUB_API + "/users/%s/repos";
  private static final String USER_BRANCHES_API = GITHUB_API + "/repos/%s/%s/branches";
  private final RestTemplate restTemplate;

  public GithubApiClient() {
    this.restTemplate = new RestTemplate();
  }

  public List<GithubRepositoryResponse> getUserRepositories(String username) {
    return List.of(
        fetchGithubApiData(
            getUserRepositoryUrl(username),
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
    headers.add("X-GitHub-Api-Version", API_VERSION);
    ResponseEntity<T> responseEntity = restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(headers),
        responseType
    );
    return responseEntity.getBody();
  }

  private String getUserRepositoryUrl(String username) {
    return String.format(USER_REPOSITORIES_API, username);
  }

  private String getUserBranchesUrl(String username, String nameRepository) {
    return String.format(USER_BRANCHES_API, username, nameRepository);
  }
}
