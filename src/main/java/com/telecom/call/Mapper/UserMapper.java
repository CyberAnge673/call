package com.telecom.call.Mapper;

import com.telecom.call.DTO.response.UserResponseDto;
import com.telecom.call.Enums.StatusType;
import com.telecom.call.Model.User;
import com.telecom.call.DTO.request.UserCreateRequestDto;

/*
 * mapper para la clase user entity (conversion de clases)
 */
public class UserMapper {

  private UserMapper(){
    throw new UnsupportedOperationException("Esta clase no se puede instanciar");
  }

  public static UserResponseDto toUser(User user) {
    if (user == null) {
      return null;
    }
    return UserResponseDto.builder().id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .userStatus(user.getUserStatusType().toString())
        .extensionId(user.getExtension().getId())
        .rolid(user.getRol().getId())
        .build();

  }

  public static User toUser(UserCreateRequestDto userCdto) {
    if (userCdto == null) {
      return null;

    }
    return User.builder()
        .name(userCdto.getName())
        .email(userCdto.getEmail())
        .password(userCdto.getPassword())
        .userStatusType(StatusType.valueOf(userCdto.getUserstatus())).build();
  }
}
