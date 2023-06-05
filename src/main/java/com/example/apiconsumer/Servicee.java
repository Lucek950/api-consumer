package com.example.apiconsumer;

import com.example.apiconsumer.github.ReadGithubApiService;
import com.example.apiconsumer.github.readModelGithubApi.BranchReadModelApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Servicee {

  private final ReadGithubApiService githubApiService;
  private final GithubRepositoryMapper githubRepositoryMapper;

  public List<ResponseRepository> getResponseRepository(String username) {
    var repositoryReadModelApiList = githubApiService.getUserRepositories(username);
    var responseRepositoryList = githubRepositoryMapper.toResponseRepositoryList(repositoryReadModelApiList);
    addBranchesToResponse(responseRepositoryList);
    return responseRepositoryList;
  }

  private void addBranchesToResponse(List<ResponseRepository> repositories) {
    for (ResponseRepository responseRepository : repositories) {
      List<BranchReadModelApi> branchReadModelApiList = githubApiService.getUserBranches(
          responseRepository.getLogin(),
          responseRepository.getNameRepository()
      );
      var responseBranches = githubRepositoryMapper.toResponseBranchList(branchReadModelApiList);
      responseRepository.setBranches(responseBranches);
    }
  }

}
