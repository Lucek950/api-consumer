package com.example.apiconsumer;

import com.example.apiconsumer.github.readModelGithubApi.BranchReadModelApi;
import com.example.apiconsumer.github.readModelGithubApi.RepositoryReadModelApi;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GithubRepositoryMapper {

  @Mapping(source = "owner.login", target = "login")
  @Mapping(source = "name", target = "nameRepository")
  ResponseRepository toResponseRepository(RepositoryReadModelApi repositoryReadModelApi);

  @Mapping(source = "name", target = "nameBranch")
  @Mapping(source = "commit.sha", target = "sha")
  ResponseBranch toResponseBranch(BranchReadModelApi branchReadModelApi);

  List<ResponseRepository> toResponseRepositoryList(List<RepositoryReadModelApi> repositoryReadModelApis);

  List<ResponseBranch> toResponseBranchList(List<BranchReadModelApi> branchReadModelApis);
}
