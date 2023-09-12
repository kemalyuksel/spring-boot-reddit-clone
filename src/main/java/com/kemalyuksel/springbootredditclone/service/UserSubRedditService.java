package com.kemalyuksel.springbootredditclone.service;

import com.kemalyuksel.springbootredditclone.dto.subreddit.UserSubRedditDto;
import com.kemalyuksel.springbootredditclone.exception.NotFoundException;
import com.kemalyuksel.springbootredditclone.model.UserSubReddit;
import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import com.kemalyuksel.springbootredditclone.repository.UserSubRedditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSubRedditService {

    private final UserSubRedditRepository userSubRedditRepository;


    public List<UserSubRedditDto> getUserSubredditsByRole(String username, SubRedditRole role) {

        List<UserSubRedditDto> dtoList = new ArrayList<>();
        List<UserSubReddit> userSubReddits = userSubRedditRepository.findAllByUserUsername(username)
                .stream()
                .filter(userSubReddit -> userSubReddit.getRole() == role)
                .toList();

        if (userSubReddits.isEmpty()) throw new NotFoundException("Subreddits for the given user not found for : " + username);

        for (UserSubReddit userSubReddit : userSubReddits) {
            UserSubRedditDto dto = new UserSubRedditDto(
                    userSubReddit.getSubreddit().getSubredditName(),
                    userSubReddit.getRole()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }


}
