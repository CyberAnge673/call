package com.telecom.call.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
  private Long id;
  private String name;
  private String email;
  private String userStatus;
  private String rolName;
  private String extensionId;
}
