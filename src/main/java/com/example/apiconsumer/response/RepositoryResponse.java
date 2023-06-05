package com.example.apiconsumer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record RepositoryResponse(

    @JsonProperty("repository_name")
    String repo_name,

    @JsonProperty("owner_login")
    String login,

    @JsonProperty("branches")
    List<BranchResponse> branches
) {

}
