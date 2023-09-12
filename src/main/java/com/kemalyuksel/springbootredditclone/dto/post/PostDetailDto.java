package com.kemalyuksel.springbootredditclone.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserInfoDto;
import com.kemalyuksel.springbootredditclone.model.enums.DescriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailDto {

    private Long id;
    private String title;
    private String description;
    private DescriptionType descriptionType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private int votes;
    private UserInfoDto userInfoDto;
    private SubRedditDto subRedditDto;
}