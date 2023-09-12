package com.kemalyuksel.springbootredditclone.controller;

import com.kemalyuksel.springbootredditclone.dto.post.PostDetailDto;
import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.UserSubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserInfoDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserUpdateDto;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import com.kemalyuksel.springbootredditclone.service.PostService;
import com.kemalyuksel.springbootredditclone.service.UserService;
import com.kemalyuksel.springbootredditclone.service.UserSubRedditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserSubRedditService userSubRedditService;
    private final PostService postService;
    private final UserService userService;


    @GetMapping("/{username}/posts")
    public ResponseEntity<List<PostDetailDto>> getAllUserPost(@PathVariable String username) {

        return ResponseEntity.ok(postService.getAllByOwner(username));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String username) {

        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/{username}/ownedSubs")
    public ResponseEntity<List<UserSubRedditDto>> getUserOwnedSubreddits(@PathVariable String username) {
        return ResponseEntity.ok(userSubRedditService.getUserSubredditsByRole(username, SubRedditRole.OWNER));
    }

    @GetMapping("/{username}/joinedSubs")
    public ResponseEntity<List<UserSubRedditDto>> getUserJoinedSubreddits(@PathVariable String username) {

        return ResponseEntity.ok(userSubRedditService.getUserSubredditsByRole(username, SubRedditRole.USER));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserUpdateDto> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable String username) {

        if(userUpdateDto.getPassword() == null) {
            Optional<User> existingUser = userService.findByUsername(username);
            userUpdateDto.setPassword(existingUser.get().getPassword());
        }

        return ResponseEntity.ok(userService.updateUserByUsername(userUpdateDto, username));
    }



}
