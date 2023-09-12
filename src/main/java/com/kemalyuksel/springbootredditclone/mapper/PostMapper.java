package com.kemalyuksel.springbootredditclone.mapper;

import com.kemalyuksel.springbootredditclone.dto.post.PostDetailDto;
import com.kemalyuksel.springbootredditclone.dto.post.PostDto;
import com.kemalyuksel.springbootredditclone.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "userInfoDto", source = "owner")
    @Mapping(target = "subRedditDto", source = "subReddit")
    PostDetailDto postToPostDetailDto(Post post);

    @Mapping(target = "owner", source = "userInfoDto")
    @Mapping(target = "subReddit", source = "subRedditDto")
    Post postDetailDtoToPost(PostDetailDto postDetailDto);

    @Mapping(target = "subredditName", source = "subReddit.subredditName")
    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto postDto);


}
