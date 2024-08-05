package com.rayan.twitter_simplified.domain.tweet.dto;

import java.util.List;

public record FeedResponse(

    List<FeedItemResponse> feedItems,
    int page,
    int pageSize,
    int totalPages,
    long totalElements

) {
}
