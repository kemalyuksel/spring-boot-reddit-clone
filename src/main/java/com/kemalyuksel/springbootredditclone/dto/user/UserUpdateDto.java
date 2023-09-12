package com.kemalyuksel.springbootredditclone.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private Long id;

    @NotBlank(message = "This field can not be empty or null")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    private String password;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "This field can not be empty or null")
    private String displayName;

    @NotBlank(message = "This field can not be empty or null")
    private String firstName;

    @NotBlank(message = "This field can not be empty or null")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String about;

    private String profile_img;
}
