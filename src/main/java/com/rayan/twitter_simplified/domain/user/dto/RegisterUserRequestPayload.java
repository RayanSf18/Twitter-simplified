package com.rayan.twitter_simplified.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestPayload(

    @NotBlank
    String username,

    @NotBlank
    String password

) {
}
