package com.kemalyuksel.springbootredditclone.mapper;

import com.kemalyuksel.springbootredditclone.dto.subreddit.SubRedditDto;
import com.kemalyuksel.springbootredditclone.dto.subreddit.UserSubRedditDto;
import com.kemalyuksel.springbootredditclone.model.SubReddit;
import com.kemalyuksel.springbootredditclone.model.UserSubReddit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {
    SubRedditMapper MAPPER = Mappers.getMapper(SubRedditMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "subredditName", source = "subredditName")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "themeImgUrl", source = "themeImgUrl")
    @Mapping(target = "subImgUrl", source = "subImgUrl")
    @Mapping(target = "bannerImgUrl", source = "bannerImgUrl")
    SubRedditDto SubRedditToSubRedditDto(SubReddit subReddit);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "subredditName", source = "subredditName")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "themeImgUrl", source = "themeImgUrl")
    @Mapping(target = "subImgUrl", source = "subImgUrl")
    @Mapping(target = "bannerImgUrl", source = "bannerImgUrl")
    SubReddit SubRedditDtoToSubReddit(SubRedditDto subRedditDto);


    UserSubRedditDto UserSubRedditToUserSubRedditDto(UserSubReddit userSubReddit);


    UserSubReddit UserSubRedditDtoToUserSubReddit(UserSubRedditDto userSubRedditDto);

    @Mapping(target = "id", source = "subreddit.id")
    @Mapping(target = "subredditName", source = "subreddit.subredditName")
    @Mapping(target = "about", source = "subreddit.about")
    @Mapping(target = "createdAt", source = "subreddit.createdAt")
    SubRedditDto UserSubRedditToSubRedditDto(UserSubReddit userSubReddit);

    @Mapping(target = "subreddit.id", source = "id")
    @Mapping(target = "subreddit.subredditName", source = "subredditName")
    @Mapping(target = "subreddit.about", source = "about")
    @Mapping(target = "subreddit.createdAt", source = "createdAt")
    UserSubReddit SubRedditDtoToUserSubReddit(SubRedditDto subRedditDto);
}