package com.kemalyuksel.springbootredditclone.dto.subreddit;


import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSubRedditDto {
    private String subredditName;
    private SubRedditRole role;
}