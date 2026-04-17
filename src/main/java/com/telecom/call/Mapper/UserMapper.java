package com.telecom.call.Mapper;

import com.telecom.call.DTO.response.UserResponseDto;
import com.telecom.call.Model.User;

/*
 * mapper para la clase user entity (conversion de clases)
 */
public class UserMapper {

  public static UserResponseDto ToUser(User user) {
    if (user == null) {
      return null;
    }
    UserResponseDto userDto = UserResponseDto.builder().id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .userStatus(user.getUserStatusType().toString())
        .extensionId(user.getExtension().getId())
        .rolid(user.getRol().getId())
        .build();
    return userDto;

  }
}
