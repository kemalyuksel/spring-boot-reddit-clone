package com.kemalyuksel.springbootredditclone.controller;

import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditUpdateDto;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.enums.DescriptionType;
import com.kemalyuksel.springbootredditclone.service.PostService;
import com.kemalyuksel.springbootredditclone.service.SubRedditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subreddits")
public class SubRedditController {

    private final SubRedditService subRedditService;
    @GetMapping()
    public ResponseEntity<List<SubRedditDto>> getAll() {
        return ResponseEntity.ok(subRedditService.getAll());
    }

    @GetMapping("/r/{subredditName}")
    public ResponseEntity<SubRedditDto> getSubredditByName(@PathVariable String subredditName){
        SubRedditDto subRedditDto = subRedditService.getByName(subredditName);
        if(subRedditDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subRedditDto);
    }
    @GetMapping("/getNames")
    public ResponseEntity<List<String>> getAllName( @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(subRedditService.getAllSubredditNamesForUser(userDetails.getUsername()));
    }

    @GetMapping("/most-popular")
    public ResponseEntity<List<SubRedditDto>> getMostPopularSubreddits() {
        return ResponseEntity.ok(subRedditService.getMostPopularSubreddit());
    }

    @PostMapping("/create")
    public ResponseEntity<SubRedditDto> createSubreddit(@RequestBody @Valid SubRedditDto subRedditDto, @AuthenticationPrincipal UserDetails userDetails) {

        SubRedditDto subDto = subRedditService.createSubReddit(subRedditDto,userDetails.getUsername() );

        return ResponseEntity.ok(subDto);
    }

    @GetMapping("/{subredditName}/isUserSubscribe")
    public ResponseEntity<Boolean> isUserSubscribe(@PathVariable String subredditName, @AuthenticationPrincipal UserDetails userDetails){

        return new ResponseEntity<>(subRedditService.isUserSubscribed(subredditName,userDetails.getUsername()),HttpStatus.OK);
    }
    @PostMapping("/{subredditName}/subscribe")
    public ResponseEntity<String> subscribeToSubreddit(@PathVariable String subredditName, @AuthenticationPrincipal UserDetails userDetails) {

        subRedditService.subscribeUserToSubreddit(subredditName,userDetails.getUsername());
        return new ResponseEntity<>("Subscribed successfully.", HttpStatus.OK);
    }
    @PostMapping("/{subredditName}/unsubscribe")
    public ResponseEntity<String> unSubscribeToSubreddit(@PathVariable String subredditName, @AuthenticationPrincipal UserDetails userDetails) {

        subRedditService.unSubscribeUserToSubreddit(subredditName,userDetails.getUsername());

        return new ResponseEntity<>("UnSubscribed successfully.", HttpStatus.OK);
    }
    @PutMapping("/update/{subredditId}")
    public ResponseEntity<SubRedditDto> updateSubreddit(@RequestBody @Valid SubRedditUpdateDto subRedditUpdateDto, @PathVariable Long subredditId) {
        return ResponseEntity.ok(subRedditService.updateSubReddit(subRedditUpdateDto, subredditId));
    }


}
