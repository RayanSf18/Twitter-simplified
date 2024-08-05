package com.rayan.twitter_simplified.controllers;

import com.rayan.twitter_simplified.domain.tweet.dto.CreateTweetRequest;
import com.rayan.twitter_simplified.domain.tweet.dto.FeedItemResponse;
import com.rayan.twitter_simplified.domain.tweet.dto.FeedResponse;
import com.rayan.twitter_simplified.services.TweetService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for handling tweet-related requests.
 */
@RestController
@RequestMapping(value = "/tweets")
public class TweetController {

    private static final Logger logger = LoggerFactory.getLogger(TweetController.class);
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    /**
     * Create a new tweet.
     *
     * @param request the tweet creation request containing tweet details.
     * @param userToken the authentication token of the user creating the tweet.
     * @return HTTP 200 OK if the tweet is created successfully.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTweet(@Valid @RequestBody CreateTweetRequest request, JwtAuthenticationToken userToken) {
        logger.info("Creating tweet for user: {}", userToken.getName());
        tweetService.createTweet(request, userToken);
        logger.info("Tweet created successfully for user: {}", userToken.getName());
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a tweet by ID.
     *
     * @param tweetId the ID of the tweet to be deleted.
     * @param userToken the authentication token of the user requesting the deletion.
     * @return HTTP 204 No Content if the tweet is deleted successfully.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId, JwtAuthenticationToken userToken) {
        logger.info("Deleting tweet with ID: {} for user: {}", tweetId, userToken.getName());
        tweetService.deleteTweet(tweetId, userToken);
        logger.info("Tweet with ID: {} deleted successfully for user: {}", tweetId, userToken.getName());
        return ResponseEntity.noContent().build();
    }

    /**
     * Get a paginated feed of tweets.
     *
     * @param page the page number to retrieve.
     * @param pageSize the number of tweets per page.
     * @return a paginated response containing the tweets feed.
     */
    @GetMapping("/feed")
    public ResponseEntity<FeedResponse> feed(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        logger.info("Retrieving tweets feed for page: {}, pageSize: {}", page, pageSize);
        Page<FeedItemResponse> tweets = tweetService.feed(page, pageSize);
        FeedResponse feedResponse = new FeedResponse(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements());
        logger.info("Tweets feed retrieved successfully for page: {}, pageSize: {}", page, pageSize);
        return ResponseEntity.ok().body(feedResponse);
    }
}
