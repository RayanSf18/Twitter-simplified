package com.rayan.twitter_simplified.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(

    @NotBlank
    String username,

    @NotBlank
    String password

) {
}
