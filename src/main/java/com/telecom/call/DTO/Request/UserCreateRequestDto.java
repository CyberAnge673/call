package com.telecom.call.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserCreateRequestDto {
  @NotBlank(message = "el nombre es obligatorio")
  private String name;
  @NotBlank(message = "el email es obligatorio")
  private String email;
  @NotBlank(message = "el password es obligatorio")
  private String password;

}
