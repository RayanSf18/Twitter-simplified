package com.rayan.twitter_simplified.exceptions.custom;

import com.rayan.twitter_simplified.exceptions.handler.TwitterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CredentialsInvalidException extends TwitterException {

          private final String detail;

          public CredentialsInvalidException(String detail) {
                    this.detail = detail;
          }

          @Override
          public ProblemDetail toProblemDetail() {
                    var problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
                    problemDetail.setTitle("User or password is invalid.");
                    problemDetail.setDetail(detail);
                    return problemDetail;
          }

}
