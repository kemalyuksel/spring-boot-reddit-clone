package com.kemalyuksel.springbootredditclone.dto.subreddit;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubRedditDto {
    private Long id;

    @NotBlank(message = "Subreddit name field cannot be blank")
    private String subredditName;
    
    private String about;
    private String bannerImgUrl;
    private String subImgUrl;
    private String themeImgUrl;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;
}
