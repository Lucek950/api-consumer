package com.example.apiconsumer.github;

import com.example.apiconsumer.github.readModelGithubApi.BranchReadModelApi;
import com.example.apiconsumer.github.readModelGithubApi.RepositoryReadModelApi;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReadGithubApiService {

  private static final String API_VERSION = "2022-11-28";
  private static final String GITHUB_API = "https://api.github.com";
  private static final String USER_REPOSITORIES_API = GITHUB_API + "/users/%s/repos";
  private static final String USER_BRANCHES_API = GITHUB_API + "/repos/%s/%s/branches";
  private final RestTemplate restTemplate;

  public ReadGithubApiService() {
    this.restTemplate = new RestTemplate();
  }

  public List<RepositoryReadModelApi> getUserRepositories(String username) {
    return List.of(
        fetchGithubApiData(
            getUserRepositoryUrl(username),
            RepositoryReadModelApi[].class
        )
    );
  }

  public List<BranchReadModelApi> getUserBranches(String username, String nameRepository) {
    return List.of(
        fetchGithubApiData(
            getUserBranchesUrl(username, nameRepository),
            BranchReadModelApi[].class
        )
    );
  }

  private <T> T fetchGithubApiData(String url, Class<T> responseType) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-GitHub-Api-Version", API_VERSION);
    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<T> responseEntity = restTemplate.exchange(
        url,
        HttpMethod.GET,
        requestEntity,
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
