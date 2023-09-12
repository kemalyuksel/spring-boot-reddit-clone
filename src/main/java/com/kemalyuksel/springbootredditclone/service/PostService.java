package com.kemalyuksel.springbootredditclone.service;

import com.kemalyuksel.springbootredditclone.dto.post.PostDetailDto;
import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.exception.NotFoundException;
import com.kemalyuksel.springbootredditclone.mapper.PostMapper;
import com.kemalyuksel.springbootredditclone.model.Post;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.model.UserSubReddit;
import com.kemalyuksel.springbootredditclone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SubRedditService subRedditService;
    private final UserService userService;

    public List<PostDetailDto> findAllBySubRedditSubredditName(String subredditName) {

        List<Post> posts = postRepository.findAllBySubRedditSubredditName(subredditName);

//        if (posts.isEmpty()) {
//            throw new NotFoundException("No posts found for subreddit with sub: " + subredditName);
//        }

        return posts.stream().map(PostMapper.MAPPER::postToPostDetailDto).collect(Collectors.toList());
    }

    public PostDetailDto getBySubredditNameAndPostId(String subredditName, Long id) {

        Post post = postRepository.findBySubReddit_SubredditNameAndId(subredditName, id);

        if (post == null) {
            throw new NotFoundException("Post not found for subredditName: " + subredditName + " and postId: " + id);
        }

        return PostMapper.MAPPER.postToPostDetailDto(post);
    }

    public List<PostDetailDto> getAllByOwner(String username) { //
        User user = userService.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found by username: " + username));

        return postRepository.findAllByOwner(user).stream().map(PostMapper.MAPPER::postToPostDetailDto).collect(Collectors.toList());
    }


    public List<PostDetailDto> getAllBySubRedditIn(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found by username: " + username));
        List<SubReddit> userSubReddits = new ArrayList<>();

        for (UserSubReddit userSubReddit : user.getUserSubreddits()) {
            userSubReddits.add(userSubReddit.getSubreddit());
        }

        return postRepository.findAllBySubRedditIn(userSubReddits).stream().map(PostMapper.MAPPER::postToPostDetailDto).collect(Collectors.toList());
    }

    public List<PostDetailDto> getPopularPostsBySubreddit(String subredditName) {
        List<Post> popularPosts = postRepository.findPopularPostsBySubreddit(subredditName);
        if (popularPosts == null || popularPosts.isEmpty()) {
            throw new NotFoundException("Popular posts not found for subreddit: " + subredditName);
        }
        return popularPosts.stream().map(PostMapper.MAPPER::postToPostDetailDto).collect(Collectors.toList());
    }

    public List<PostDetailDto> getRecentPostsBySubreddit(String subredditName) {
        List<Post> recentPosts = postRepository.findRecentPostsBySubreddit(subredditName);
        if (recentPosts == null || recentPosts.isEmpty()) {
            throw new NotFoundException("Recent posts not found for subreddit: " + subredditName);
        }
        return recentPosts.stream().map(PostMapper.MAPPER::postToPostDetailDto).collect(Collectors.toList());
    }

    public PostDto createPost(PostDto postDto, String username) {

        SubReddit subReddit = subRedditService.findByName(postDto.getSubredditName());

        User user = userService.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found by username: " + username));

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDescriptionType(postDto.getDescriptionType());
        post.setCreatedAt(LocalDateTime.now());
        post.setSubReddit(subReddit);
        post.setOwner(user);

        postRepository.save(post);

        return PostMapper.MAPPER.postToPostDto(post);
    }

    public PostDetailDto updatePost(Long id, Post updatedPost) {

        Post existingPost = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found by id: " + id));

        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setDescription(updatedPost.getDescription());
            existingPost.setDescriptionType(updatedPost.getDescriptionType());

            postRepository.save(existingPost);

            return PostMapper.MAPPER.postToPostDetailDto(existingPost);
        }
        return null;
    }

    public void deleteById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException("Post not found by id: " + id);
        }
        postRepository.deleteById(id);
    }

    public List<PostDto> getAllByQuery(String query) {
        List<Post> posts = postRepository.findAllByTitleOrDescriptionContaining(query);
        return posts.stream()
                .map(PostMapper.MAPPER::postToPostDto)
                .collect(Collectors.toList());
    }


}
