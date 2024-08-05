package com.rayan.twitter_simplified.services;

import com.rayan.twitter_simplified.domain.tweet.Tweet;
import com.rayan.twitter_simplified.domain.tweet.dto.CreateTweetRequest;
import com.rayan.twitter_simplified.domain.tweet.dto.FeedItemResponse;
import com.rayan.twitter_simplified.domain.user.User;
import com.rayan.twitter_simplified.exceptions.custom.PermissionDeniedException;
import com.rayan.twitter_simplified.exceptions.custom.TweetNotFoundException;
import com.rayan.twitter_simplified.exceptions.custom.UserNotFoundException;
import com.rayan.twitter_simplified.repositories.TweetRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service responsible for managing tweets.
 */
@Service
public class TweetService {

    private static final Logger logger = LoggerFactory.getLogger(TweetService.class);

    private final TweetRepository tweetRepository;
    private final UserService userService;

    public TweetService(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }

    /**
     * Creates a new tweet.
     *
     * @param request   the request containing tweet content.
     * @param userToken the authentication token of the user creating the tweet.
     */
    @Transactional
    public void createTweet(CreateTweetRequest request, JwtAuthenticationToken userToken) {
        User user = getUserFromToken(userToken);
        Tweet newTweet = buildNewTweet(request, user);
        tweetRepository.save(newTweet);
        logger.info("Tweet created successfully by user: {} with content: {}", user.getUsername(), request.content());
    }

    /**
     * Deletes an existing tweet.
     *
     * @param tweetId   the ID of the tweet to be deleted.
     * @param userToken the authentication token of the user requesting the deletion.
     */
    @Transactional
    public void deleteTweet(Long tweetId, JwtAuthenticationToken userToken) {
        Tweet tweet = findTweetById(tweetId);
        UUID userId = UUID.fromString(userToken.getName());

        if (canUserDeleteTweet(tweet, userId)) {
            tweetRepository.deleteById(tweetId);
            logger.info("Tweet with ID: {} deleted successfully by user: {}", tweetId, userId);
        } else {
            logger.warn("User: {} attempted to delete tweet with ID: {} without permission.", userId, tweetId);
            throw new PermissionDeniedException("This operation cannot be performed without permission from the author");
        }
    }

    /**
     * Retrieves a paginated feed of tweets.
     *
     * @param page     the page number.
     * @param pageSize the number of tweets per page.
     * @return a page of feed item responses.
     */
    public Page<FeedItemResponse> feed(int page, int pageSize) {
        Page<Tweet> tweets = tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "createdAt"));
        logger.info("Retrieved feed with page: {} and pageSize: {}", page, pageSize);
        return tweets.map(this::convertToFeedItemResponse);
    }

    private FeedItemResponse convertToFeedItemResponse(Tweet tweet) {
        return new FeedItemResponse(
            tweet.getId(),
            tweet.getContent(),
            tweet.getUser().getUsername(),
            tweet.getCreatedAt());
    }

    private boolean canUserDeleteTweet(Tweet tweet, UUID userId) {
        boolean isAdmin = userService.isUserAdmin(userId);
        boolean isOwner = isTweetFromUser(tweet, userId);
        boolean canDelete = isAdmin || isOwner;

        if (!canDelete) {
            logger.warn("User: {} does not have permission to delete tweet with ID: {}", userId, tweet.getId());
        }

        return canDelete;
    }

    private Tweet findTweetById(Long tweetId) {
        return tweetRepository.findById(tweetId)
            .orElseThrow(() -> {
                logger.error("Tweet with ID: {} not found.", tweetId);
                return new TweetNotFoundException("Tweet not found with id: " + tweetId);
            });
    }

    private Tweet buildNewTweet(CreateTweetRequest request, User user) {
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setContent(request.content());
        return tweet;
    }

    private User getUserFromToken(JwtAuthenticationToken userToken) {
        UUID userId = UUID.fromString(userToken.getName());
        User user = userService.findUserById(userId);
        logger.debug("Retrieved user: {} from token: {}", userId, user.getUsername());
        return user;
    }

    private boolean isTweetFromUser(Tweet tweet, UUID userId) {
        return tweet.getUser().getId().equals(userId);
    }
}
