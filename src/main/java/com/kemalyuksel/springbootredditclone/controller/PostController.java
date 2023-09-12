package com.kemalyuksel.springbootredditclone.controller;

import com.kemalyuksel.springbootredditclone.dto.post.PostDetailDto;
import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.model.Post;
import com.kemalyuksel.springbootredditclone.service.PostService;
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
@RequestMapping("/api/v1/posts")
//POST ID long YERİNE string KULLANILACAK.
public class PostController {

    private final PostService postService;
    @GetMapping("/r/{subredditName}")
    public ResponseEntity<List<PostDetailDto>> getPostsBySubredditName(@PathVariable String subredditName) {
        return ResponseEntity.ok(postService.findAllBySubRedditSubredditName(subredditName));
    }
    @GetMapping("/r/{subredditName}/comments/{postId}")
    public ResponseEntity<PostDetailDto> getPostDetails(@PathVariable String subredditName, @PathVariable Long postId) {

        return ResponseEntity.ok(postService.getBySubredditNameAndPostId(subredditName, postId));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDetailDto>> getAllPostsByUserSubReddits(@AuthenticationPrincipal UserDetails userDetails) {
        String username="test";
        List<PostDetailDto> posts = postService.getAllBySubRedditIn(userDetails.getUsername());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/r/{subredditName}/popular")
    public List<PostDetailDto> getPopularPostsBySubreddit(@PathVariable String subredditName) {
        return postService.getPopularPostsBySubreddit(subredditName);
    }

    @GetMapping("/r/{subredditName}/recent")
    public List<PostDetailDto> getRecentPostsBySubreddit(@PathVariable String subredditName) {
        return postService.getRecentPostsBySubreddit(subredditName);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto, @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(postService.createPost(postDto, userDetails.getUsername() ));
    }

    @PutMapping("/r/{postId}")
    public ResponseEntity<PostDetailDto> updatePost(@PathVariable Long postId, @RequestBody @Valid Post updatedPost) {

        PostDetailDto updatedPostResult = postService.updatePost(postId, updatedPost);

        if (updatedPostResult != null) {
            return ResponseEntity.ok(updatedPostResult); // 200 OK yanıtı ve güncellenmiş gönderi
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found yanıtı, güncellenecek gönderi bulunamadı
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.ok("Post has been deleted successfully");
    }

}
