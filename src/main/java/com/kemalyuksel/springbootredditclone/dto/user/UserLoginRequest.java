package com.kemalyuksel.springbootredditclone.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @NotBlank(message = "Username field cannot be blank")
    private String username;
    @NotBlank(message = "Password field cannot be blank")
    private String password;
}