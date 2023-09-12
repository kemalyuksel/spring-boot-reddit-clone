package com.kemalyuksel.springbootredditclone.security;

import com.kemalyuksel.springbootredditclone.dto.user.UserRegisterDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserLoginRequest;
import com.kemalyuksel.springbootredditclone.dto.user.UserResponse;
import com.kemalyuksel.springbootredditclone.exception.UnAuthorizedException;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.model.enums.UserRole;
import com.kemalyuksel.springbootredditclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserRegisterDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(UserRole.USER);
        user.setAbout("");
        user.setFirstName("");
        user.setLastName("");
        user.setDisplayName("");
        user.setProfile_img("https://styles.redditmedia.com/t5_2r1ox/styles/communityIcon_pgozoalsji9b1.png");
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    public UserResponse auth(UserLoginRequest userRequest) {
//        Authentication authenticated= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
//        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
//        String token = jwtService.generateToken(user);
//        return UserResponse.builder().token(token).build();
        try {
            Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
            User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
            String token = jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();
        } catch (AuthenticationException ex) {
            throw new UnAuthorizedException("Username or password invalid.");
        }
    }


}
