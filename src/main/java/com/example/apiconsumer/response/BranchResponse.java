package com.example.apiconsumer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BranchResponse(

    @JsonProperty("branch_name")
    String branchName,

    @JsonProperty("commit_sha")
    String sha
) {

}
