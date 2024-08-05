package com.rayan.twitter_simplified.dto;

public record LoginResponse(

    String accessToken,
    Long expiresIn

) {
}
