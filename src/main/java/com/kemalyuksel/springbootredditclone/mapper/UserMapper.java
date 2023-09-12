package com.kemalyuksel.springbootredditclone.mapper;

import com.kemalyuksel.springbootredditclone.dto.user.UserInfoDto;
import com.kemalyuksel.springbootredditclone.dto.user.UserUpdateDto;
import com.kemalyuksel.springbootredditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserInfoDto userToUserInfoDto(User user);

    User UserInfoDtoToUser(UserInfoDto userInfoDto);

    UserUpdateDto userToUserUpdateDto(User user);
    User UserUpdateDtoToUser(UserUpdateDto userUpdateDto);

}
