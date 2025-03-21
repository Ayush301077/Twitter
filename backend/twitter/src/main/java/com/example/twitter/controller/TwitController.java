package com.example.twitter.controller;


import com.example.twitter.dto.TwitDto;
import com.example.twitter.dto.mapper.TwitDtoMapper;
import com.example.twitter.exception.TwitException;
import com.example.twitter.exception.UserException;
import com.example.twitter.model.Twit;
import com.example.twitter.model.User;
import com.example.twitter.request.TwitReplyRequest;
import com.example.twitter.response.ApiResponse;
import com.example.twitter.service.TwitService;
import com.example.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/twits")
//@Tag(name="Twit Management", description = "Endpoints for managing twits")
public class TwitController {

    @Autowired
    private TwitService twitService;
    @Autowired
    private UserService userService;

//    public TwitController(TwitService twitService,UserService userService) {
//        this.twitService=twitService;
//        this.userService=userService;
//    }

    @PostMapping("/create")
    public ResponseEntity<TwitDto> createTwit(@RequestBody Twit req,
                                              @RequestHeader("Authorization") String jwt) throws UserException, TwitException {

//        System.out.println("content + "+req.getContent());
        User user=userService.findUserProfileByJwt(jwt);
        Twit twit=twitService.createTwit(req, user);

        TwitDto twitDto= TwitDtoMapper.toTwitDto(twit,user);

        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);
    }

    @PostMapping("/reply")
    public ResponseEntity<TwitDto> replyTwit(@RequestBody TwitReplyRequest req,
                                             @RequestHeader("Authorization") String jwt) throws UserException, TwitException{


        User user=userService.findUserProfileByJwt(jwt);
        Twit twit=twitService.createReply(req, user);

        TwitDto twitDto=TwitDtoMapper.toTwitDto(twit,user);

        return new ResponseEntity<>(twitDto,HttpStatus.CREATED);
    }

    @PutMapping("/{twitId}/retwit")
    public ResponseEntity<TwitDto> retwit( @PathVariable Long twitId,
                                           @RequestHeader("Authorization") String jwt) throws UserException, TwitException{

        User user=userService.findUserProfileByJwt(jwt);

        Twit twit=twitService.retwit(twitId, user);

        TwitDto twitDto=TwitDtoMapper.toTwitDto(twit,user);

        return new ResponseEntity<>(twitDto,HttpStatus.OK);
    }

    @GetMapping("/{twitId}")
    public ResponseEntity<TwitDto> findTwitById( @PathVariable Long twitId,
                                                 @RequestHeader("Authorization") String jwt) throws TwitException, UserException{
        User user=userService.findUserProfileByJwt(jwt);
        Twit twit=twitService.findById(twitId);

        TwitDto twitDto=TwitDtoMapper.toTwitDto(twit,user);

        return new ResponseEntity<>(twitDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{twitId}")
    public ResponseEntity<ApiResponse> deleteTwitById( @PathVariable Long twitId,
                                                       @RequestHeader("Authorization") String jwt) throws UserException, TwitException{

        User user=userService.findUserProfileByJwt(jwt);

        twitService.deleteTwitById(twitId, user.getId());

        ApiResponse res=new ApiResponse();
        res.setMessage("twit deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<TwitDto>> findAllTwits(@RequestHeader("Authorization") String jwt) throws UserException{
        User user=userService.findUserProfileByJwt(jwt);
        List<Twit> twits=twitService.findAllTwit();
        List<TwitDto> twitDtos=TwitDtoMapper.toTwitDtos(twits,user);
        return new ResponseEntity<List<TwitDto>>(twitDtos,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TwitDto>> getUsersTwits(@PathVariable Long userId,
                                                       @RequestHeader("Authorization") String jwt)
            throws UserException{
        User reqUser=userService.findUserProfileByJwt(jwt);
//        User user=userService.findUserById(userId);
        List<Twit> twits=twitService.getUsersTwit(reqUser);
        List<TwitDto> twitDtos=TwitDtoMapper.toTwitDtos(twits,reqUser);
        return new ResponseEntity<List<TwitDto>>(twitDtos,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TwitDto>> findTwitByLikesContainsUser(@PathVariable Long userId,
                                                                     @RequestHeader("Authorization") String jwt)
            throws UserException{
        User reqUser=userService.findUserProfileByJwt(jwt);
//        User user=userService.findUserById(userId);
        List<Twit> twits=twitService.findByLikesContainsUser(reqUser);
        List<TwitDto> twitDtos=TwitDtoMapper.toTwitDtos(twits,reqUser);
        return new ResponseEntity<List<TwitDto>>(twitDtos,HttpStatus.OK);
    }

}

