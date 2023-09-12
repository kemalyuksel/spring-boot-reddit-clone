package com.kemalyuksel.springbootredditclone.service;

import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserInfoDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserUpdateDto;
import com.kemalyuksel.springbootredditclone.exception.NotFoundException;
import com.kemalyuksel.springbootredditclone.mapper.UserMapper;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserInfoDto getUserByUsername(String username) {
        return UserMapper.MAPPER.userToUserInfoDto(userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found by given username: " + username)));
    }

    public UserUpdateDto updateUserByUsername(UserUpdateDto userUpdateDto, String username) {

        User existingUser = UserMapper.MAPPER.UserInfoDtoToUser(getUserByUsername(username));
        if (existingUser == null) {
            throw new NotFoundException("User not found by given username : " + username);
        }

        existingUser.setUsername(userUpdateDto.getUsername());
        existingUser.setPassword(userUpdateDto.getPassword());
        existingUser.setEmail(userUpdateDto.getEmail());
        existingUser.setDisplayName(userUpdateDto.getDisplayName() != null ? userUpdateDto.getDisplayName().trim() : "");
        existingUser.setFirstName(userUpdateDto.getFirstName() != null ? userUpdateDto.getFirstName().trim() : "");
        existingUser.setLastName(userUpdateDto.getLastName() != null ? userUpdateDto.getLastName().trim() : "");
        existingUser.setDateOfBirth(userUpdateDto.getDateOfBirth());
        existingUser.setAbout(userUpdateDto.getAbout() != null ? userUpdateDto.getAbout().trim() : "");
        existingUser.setProfile_img(userUpdateDto.getProfile_img());


        return UserMapper.MAPPER.userToUserUpdateDto(userRepository.save(existingUser));
    }


    public List<UserInfoDto> getAllByQuery(String query) {
        List<User> users = userRepository.findAllByUsernameContaining(query);

        return users.stream().map(userMapper::userToUserInfoDto)
                .collect(Collectors.toList());
    }

}
