package com.rayan.twitter_simplified.domain.tweet.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTweetRequest(

    @NotBlank
    String content

) {
}
