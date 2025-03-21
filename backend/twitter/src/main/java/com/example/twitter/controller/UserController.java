package com.example.twitter.controller;


import com.example.twitter.dto.UserDto;
import com.example.twitter.dto.mapper.UserDtoMapper;
import com.example.twitter.exception.UserException;
import com.example.twitter.model.User;
import com.example.twitter.service.UserService;
import com.example.twitter.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@Tag(name="User Management", description = "Endpoints for managing user profiles and information")
public class UserController {

    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService=userService;
//    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfileHandler(@RequestHeader("Authorization") String jwt)
            throws UserException {

        User user=userService.findUserProfileByJwt(jwt);
//        user.setPassword(null);
//        user.setReq_user(true);
        UserDto userDto= UserDtoMapper.toUserDto(user);
        userDto.setReq_user(true);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByIdHandler(@PathVariable Long userId,
                                                      @RequestHeader("Authorization") String jwt)
            throws UserException{

        User reqUser=userService.findUserProfileByJwt(jwt);

        User user=userService.findUserById(userId);

//		user.setReq_user(UserUtil.isReqUser(reqUser, user));

        UserDto userDto=UserDtoMapper.toUserDto(user);
        userDto.setReq_user(UserUtil.isReqUser(reqUser, user));
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, user));
        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUserHandler(@RequestParam String query,
                                                           @RequestHeader("Authorization") String jwt)
            throws UserException{

        User reqUser=userService.findUserProfileByJwt(jwt);

        List<User> users=userService.searchUser(query);

//		user.setReq_user(UserUtil.isReqUser(reqUser, user));

        List<UserDto> userDtos=UserDtoMapper.toUserDtos(users);

        return new ResponseEntity<>(userDtos,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUserHandler(@RequestBody User req,
                                                     @RequestHeader("Authorization") String jwt)
            throws UserException{

//        System.out.println("update user  "+req);
        User reqUser =userService.findUserProfileByJwt(jwt);

        User user=userService.updateUser(reqUser.getId(), req);
//        updatedUser.setPassword(null);
        UserDto userDto=UserDtoMapper.toUserDto(user);
//        userDto.setReq_user(true);
        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<UserDto> followUserHandler(@PathVariable Long userId, @RequestHeader("Authorization") String jwt)
            throws UserException{

        User reqUser=userService.findUserProfileByJwt(jwt);

        User user=userService.followUser(userId, reqUser);
        UserDto userDto=UserDtoMapper.toUserDto(user);
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, user));
        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }


}
