package com.kemalyuksel.springbootredditclone.dto.post;

import com.kemalyuksel.springbootredditclone.model.enums.DescriptionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    @NotBlank(message = "Title field cannot be blank")
    private String title;

    @NotBlank(message = "Description field cannot be blank")
    private String description;

    private DescriptionType descriptionType;

    @NotBlank(message = "Please select a community")
    private String subredditName;
}
