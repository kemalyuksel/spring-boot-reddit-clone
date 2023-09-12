package com.kemalyuksel.springbootredditclone.dto;

import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResults {

    List<SubRedditDto> subreddits;
    List<PostDto> posts;
    List<UserInfoDto> users;

}
