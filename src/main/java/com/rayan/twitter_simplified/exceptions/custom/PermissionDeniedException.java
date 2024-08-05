package com.rayan.twitter_simplified.exceptions.custom;

import com.rayan.twitter_simplified.exceptions.handler.TwitterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PermissionDeniedException extends TwitterException {

          private final String detail;

          public PermissionDeniedException(String detail) {
                    this.detail = detail;
          }

          @Override
          public ProblemDetail toProblemDetail() {
                    var problemDetail = ProblemDetail.forStatus(HttpStatus.METHOD_NOT_ALLOWED);
                    problemDetail.setTitle("Permission denied");
                    problemDetail.setDetail(detail);
                    return problemDetail;
          }

}
