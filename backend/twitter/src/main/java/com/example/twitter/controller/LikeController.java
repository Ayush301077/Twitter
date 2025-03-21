package com.example.twitter.controller;


import com.example.twitter.dto.LikeDto;
import com.example.twitter.dto.mapper.LikeDtoMapper;
import com.example.twitter.exception.LikeException;
import com.example.twitter.exception.TwitException;
import com.example.twitter.exception.UserException;
import com.example.twitter.model.Like;
import com.example.twitter.model.User;
import com.example.twitter.service.LikesService;
import com.example.twitter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@Tag(name="Like-Unlike Twit")
public class LikeController {

    private UserService userService;
    private LikesService likeService;

    public LikeController(UserService userService, LikesService likeService) {
        this.userService=userService;
        this.likeService=likeService;
    }

    @PostMapping("/{twitId}/like")
    public ResponseEntity<LikeDto> likeTwit(
            @PathVariable Long twitId,
            @RequestHeader("Authorization") String jwt) throws UserException, TwitException {

        User user=userService.findUserProfileByJwt(jwt);
        Like like =likeService.likeTwit(twitId, user);

        LikeDto likeDto= LikeDtoMapper.toLikeDto(like,user);

        return new ResponseEntity<>(likeDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{twitId}/unlike")
    public ResponseEntity<LikeDto>unlikeTwit(
            @PathVariable Long twitId,
            @RequestHeader("Authorization") String jwt) throws UserException, TwitException, LikeException {

        User user=userService.findUserProfileByJwt(jwt);
        Like like =likeService.unlikeTwit(twitId, user);


        LikeDto likeDto=LikeDtoMapper.toLikeDto(like,user);
        return new ResponseEntity<>(likeDto,HttpStatus.CREATED);
    }

    @GetMapping("/twit/{twitId}")
    public ResponseEntity<List<LikeDto>>getAllLike(
            @PathVariable Long twitId,@RequestHeader("Authorization") String jwt) throws UserException, TwitException{
        User user=userService.findUserProfileByJwt(jwt);

        List<Like> likes =likeService.getAllLikes(twitId);

        List<LikeDto> likeDtos=LikeDtoMapper.toLikeDtos(likes,user);

        return new ResponseEntity<>(likeDtos,HttpStatus.CREATED);
    }


}

