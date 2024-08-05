package com.rayan.twitter_simplified.exceptions.custom;

import com.rayan.twitter_simplified.exceptions.handler.TwitterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistsException extends TwitterException {

          private final String detail;

          public UserAlreadyExistsException(String detail) {
                    this.detail = detail;
          }

          @Override
          public ProblemDetail toProblemDetail() {
                    var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
                    problemDetail.setTitle("User already exists.");
                    problemDetail.setDetail(detail);
                    return problemDetail;
          }

}
