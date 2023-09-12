package com.kemalyuksel.springbootredditclone.controller;

import com.kemalyuksel.springbootredditclone.dto.user.UserRegisterDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserLoginRequest;
import com.kemalyuksel.springbootredditclone.dto.user.UserResponse;
import com.kemalyuksel.springbootredditclone.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins ="http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRegisterDto userDto) {
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserResponse> auth(@RequestBody @Valid UserLoginRequest userRequest) {
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        return ResponseEntity.ok("Logout Success");
    }

}
