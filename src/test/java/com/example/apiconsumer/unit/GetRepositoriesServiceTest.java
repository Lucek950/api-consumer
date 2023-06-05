package com.example.apiconsumer.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.example.apiconsumer.GetRepositoriesService;
import com.example.apiconsumer.TestData;
import com.example.apiconsumer.githubApi.GithubApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetRepositoriesServiceTest {

  @Mock
  private GithubApiClient githubApiClient;

  @InjectMocks
  private GetRepositoriesService getRepositoriesService;

  @Test
  void should_return_a_list_of_repositories_for_a_given_username() {
    //given
    var repos = TestData.createGithubRepositories();
    given(githubApiClient.getUserRepositories(any()))
        .willReturn(repos);

    //when
    var result = getRepositoriesService.getRepositoriesForUserLogin(TestData.getTestUsername());

    //then
    then(githubApiClient).should(times(1)).getUserRepositories(any());

    assertAll(
        () -> assertThat(result).size().isEqualTo(repos.size()),
        () -> {
          for (int i = 0; i < result.size(); i++) {
            int finalI = i;
            assertAll(
                () -> assertThat(result.get(finalI).repo_name()).isEqualTo(repos.get(finalI).name()),
                () -> assertThat(result.get(finalI).login()).isEqualTo(repos.get(finalI).owner().login())
            );
          }
        }
    );
  }

  @Test
  void should_return_a_empty_list_of_repositories_for_a_given_username_when_git_hub_repositories_has_forks() {
    //given
    var forks = TestData.createForksGithubRepositories();
    given(githubApiClient.getUserRepositories(any()))
        .willReturn(forks);

    //when
    var result = getRepositoriesService.getRepositoriesForUserLogin(TestData.getTestUsername());

    //then
    then(githubApiClient).should(times(1)).getUserRepositories(any());

    assertAll(
        () -> assertThat(result).isEmpty()
    );
  }
}
