package com.rayan.twitter_simplified.exceptions.custom;

import com.rayan.twitter_simplified.exceptions.handler.TwitterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TweetNotFoundException extends TwitterException {

          private final String detail;

          public TweetNotFoundException(String detail) {
                    this.detail = detail;
          }

          @Override
          public ProblemDetail toProblemDetail() {
                    var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
                    problemDetail.setTitle("Tweet not found.");
                    problemDetail.setDetail(detail);
                    return problemDetail;
          }

}
