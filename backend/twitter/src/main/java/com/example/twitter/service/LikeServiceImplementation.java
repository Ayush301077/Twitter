package com.example.twitter.service;

import com.example.twitter.exception.LikeException;
import com.example.twitter.exception.TwitException;
import com.example.twitter.exception.UserException;
import com.example.twitter.model.Like;
import com.example.twitter.model.Twit;
import com.example.twitter.model.User;
import com.example.twitter.repository.LikeRepository;
import com.example.twitter.repository.TwitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements LikesService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private TwitService twitService;
    @Autowired
    private TwitRepository twitRepository;

//    public LikeServiceImplementation(
//            LikeRepository likeRepository,
//            TwitService twitService,
//            TwitRepository twitRepository) {
//        this.likeRepository=likeRepository;
//        this.twitService=twitService;
//        this.twitRepository=twitRepository;
//    }

    @Override
    public Like likeTwit(Long twitId, User user) throws UserException, TwitException {

        Like isLikeExist=likeRepository.isLikeExist(user.getId(), twitId);

        if(isLikeExist!=null) {
            likeRepository.deleteById(isLikeExist.getId());
            return isLikeExist;
        }

        Twit twit=twitService.findById(twitId);
        Like like=new Like();
        like.setTwit(twit);
        like.setUser(user);

        Like savedLike=likeRepository.save(like);


        twit.getLikes().add(savedLike);
        twitRepository.save(twit);

        return savedLike;
    }

    @Override
    public Like unlikeTwit(Long twitId, User user) throws UserException, TwitException, LikeException {
//        Like like=likeRepository.findById(twitId).orElseThrow(()->new LikeException("Like Not Found"));
//
//        if(like.getUser().getId().equals(user.getId())) {
//            throw new UserException("something went wrong...");
//        }
//
//        likeRepository.deleteById(like.getId());
//        return like;

        return null;
    }

    @Override
    public List<Like> getAllLikes(Long twitId) throws TwitException {
        Twit twit=twitService.findById(twitId);

        List<Like> likes=likeRepository.findByTwitId(twit.getId());
        return likes;
    }

}

