package com.kemalyuksel.springbootredditclone.service;

import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditUpdateDto;
import com.kemalyuksel.springbootredditclone.exception.NotFoundException;
import com.kemalyuksel.springbootredditclone.mapper.PostMapper;
import com.kemalyuksel.springbootredditclone.mapper.SubRedditMapper;
import com.kemalyuksel.springbootredditclone.mapper.UserMapper;
import com.kemalyuksel.springbootredditclone.model.Post;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.User;
import com.kemalyuksel.springbootredditclone.model.UserSubReddit;
import com.kemalyuksel.springbootredditclone.model.enums.DescriptionType;
import com.kemalyuksel.springbootredditclone.model.enums.SubRedditRole;
import com.kemalyuksel.springbootredditclone.repository.SubRedditRepository;
import com.kemalyuksel.springbootredditclone.repository.UserSubRedditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SubRedditService {

    private final SubRedditRepository subRedditRepository;
    private final UserSubRedditRepository userSubRedditRepository;
    private final UserService userService;
    private final SubRedditMapper subRedditMapper;

    public SubRedditDto getByName(String subredditName){
        return subRedditMapper.SubRedditToSubRedditDto(findByName(subredditName));
    }
    public List<SubRedditDto> getAll() {
        return subRedditRepository.findAll().stream().map(SubRedditMapper.MAPPER::SubRedditToSubRedditDto).collect(Collectors.toList());
    }

    public List<String> getAllSubredditNamesForUser(String username) {

        List<String>  subredditNames = new ArrayList<>();

        for(String sub : subRedditRepository.getAllSubredditName()){

            if(isUserSubscribed(sub,username)) subredditNames.add(sub);

        }

        return subredditNames;
    }


    public SubRedditDto getById(Long id) {

        SubReddit subReddit = subRedditRepository.findById(id).orElseThrow(() -> new NotFoundException("Subreddit not found by given id : " + id));

        return SubRedditMapper.MAPPER.SubRedditToSubRedditDto(subReddit);
    }

    public List<SubRedditDto> getMostPopularSubreddit() {

        return userSubRedditRepository.findMostPopularSubreddit().stream().map(SubRedditMapper.MAPPER::SubRedditToSubRedditDto).collect(Collectors.toList());
    }

    public SubReddit findByName(String name){
        return subRedditRepository.findBySubredditName(name);
    }


    public void subscribeUserToSubreddit(String subredditName, String username) {

        SubReddit subReddit = subRedditRepository.findBySubredditName(subredditName);

        if (subReddit == null) throw new NotFoundException("Subreddit not found " + subredditName);

        User user = UserMapper.MAPPER.UserInfoDtoToUser(userService.getUserByUsername(username));

        UserSubReddit userSubReddit = new UserSubReddit();
        userSubReddit.setSubreddit(subReddit);
        userSubReddit.setUser(user);
        userSubReddit.setRole(SubRedditRole.USER);

        userSubRedditRepository.save(userSubReddit);
    }


    public void unSubscribeUserToSubreddit(String subredditName, String username) {

        SubReddit subReddit = subRedditRepository.findBySubredditName(subredditName);

        if (subReddit == null) {
            throw new NotFoundException("Subreddit not found by given name: " + subredditName);
        }

        User user = UserMapper.MAPPER.UserInfoDtoToUser(userService.getUserByUsername(username));

        if (user == null) {
            throw new NotFoundException("User not found by given username: " + username);
        }

        UserSubReddit userSubReddit = userSubRedditRepository.findByUserAndSubredditAndRole(user, subReddit, SubRedditRole.USER);

        if (userSubReddit == null) {
            throw new NotFoundException("UserSubReddit not found for the given user and subreddit");
        }

        userSubRedditRepository.deleteById(userSubReddit.getId());

    }

    public boolean isUserSubscribed(String subredditName, String username) {
        return userSubRedditRepository.existsBySubreddit_SubredditNameAndUser_Username(subredditName, username);
    }

    public SubRedditDto createSubReddit(SubRedditDto subRedditDto, String username) {

        subRedditDto.setCreatedAt(LocalDate.now());
        subRedditDto.setBannerImgUrl("https://codahosted.io/packs/10796/unversioned/assets/COVER/51263089de62c756dbcbf9ec93bfcc16b4accec201508dcd480d2daa2430cd2761cb7d97e5ffb41fa8f37bbae4f3682458430a47d6cf77f6d6517f7ee9881b29b1e2daae096597bf35a6e95ce71b2717d8d2b6eda08eb53064484d9f62fe487a05451a15");
        subRedditDto.setSubImgUrl("https://toppng.com/uploads/preview/reddit-logo-reddit-icon-115628658968pe8utyxjt.png");
        subRedditDto.setThemeImgUrl("https://img.freepik.com/free-photo/abstract-smooth-empty-grey-studio-well-use-as-background-business-report-digital-website-template-backdrop_1258-55963.jpg?w=2000");
        SubReddit subreddit = subRedditRepository.save(SubRedditMapper.MAPPER.SubRedditDtoToSubReddit(subRedditDto));

        UserSubReddit userSubReddit = new UserSubReddit();
        userSubReddit.setSubreddit(subreddit);
        userSubReddit.setUser(UserMapper.MAPPER.UserInfoDtoToUser(userService.getUserByUsername(username)));
        userSubReddit.setRole(SubRedditRole.OWNER);

        userSubRedditRepository.save(userSubReddit);

        return SubRedditMapper.MAPPER.SubRedditToSubRedditDto(subreddit);
    }

    public SubRedditDto updateSubReddit(SubRedditUpdateDto subRedditUpdateDto, Long id) {

        SubReddit existingSub = subRedditRepository.findById(id).orElseThrow(() -> new NotFoundException("Subreddit not found by given id : " + id));

        existingSub.setAbout(subRedditUpdateDto.getAbout());
        existingSub.setSubImgUrl(subRedditUpdateDto.getSubImgUrl());
        existingSub.setBannerImgUrl(subRedditUpdateDto.getBannerImgUrl());
        existingSub.setThemeImgUrl(subRedditUpdateDto.getThemeImgUrl());
        return SubRedditMapper.MAPPER.SubRedditToSubRedditDto(subRedditRepository.save(existingSub));

    }

    public List<SubRedditDto> getAllByQuery(String query) {

        List<SubReddit> subReddits = subRedditRepository.findAllByTitleContaining(query);

        return subReddits.stream().map(subRedditMapper::SubRedditToSubRedditDto)
                .collect(Collectors.toList());
    }


}
