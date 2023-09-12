package com.kemalyuksel.springbootredditclone.dto.subreddit;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubRedditUpdateDto {

    private Long id;
    private String subredditName;
    private String about;
    private String bannerImgUrl;
    private String subImgUrl;
    private String themeImgUrl;

}
