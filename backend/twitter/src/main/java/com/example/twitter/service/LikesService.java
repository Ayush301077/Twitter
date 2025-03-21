package com.example.twitter.service;

import com.example.twitter.exception.LikeException;
import com.example.twitter.exception.TwitException;
import com.example.twitter.exception.UserException;
import com.example.twitter.model.Like;
import com.example.twitter.model.User;

import java.util.List;

public interface LikesService {
    public Like likeTwit(Long twitId, User user) throws UserException, TwitException;

    public Like unlikeTwit(Long twitId, User user) throws UserException, TwitException, LikeException;

    public List<Like> getAllLikes(Long twitId) throws TwitException;

}
