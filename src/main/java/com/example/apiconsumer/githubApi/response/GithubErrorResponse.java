package com.example.apiconsumer.githubApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubErrorResponse(

    @JsonProperty("message")
    String message,

    @JsonProperty("documentation_url")
    String documentationUrl
) {

}
