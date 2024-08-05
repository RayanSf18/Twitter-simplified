package com.rayan.twitter_simplified.repositories;

import com.rayan.twitter_simplified.domain.tweet.Tweet;
import com.rayan.twitter_simplified.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
