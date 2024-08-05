package com.rayan.twitter_simplified.domain.tweet.dto;

import java.time.Instant;

public record FeedItemResponse(

    Long tweetId,
    String content,
    String username,
    Instant createdAt
) {
}
