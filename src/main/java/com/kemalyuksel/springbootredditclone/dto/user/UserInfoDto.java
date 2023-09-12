package com.kemalyuksel.springbootredditclone.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemalyuksel.springbootredditclone.model.enums.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String username;
    private String email;
    private String displayName;
    private String firstName;
    private String lastName;
    private UserRole role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastOnline;

    private String about;
    private String profile_img;
}
