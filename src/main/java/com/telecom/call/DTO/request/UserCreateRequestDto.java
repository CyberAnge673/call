package com.telecom.call.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {
  @NotBlank(message = "el nombre es obligatorio")
  private String name;
  @NotBlank(message = "el email es obligatorio")
  private String email;
  @NotBlank(message = "el password es obligatorio")
  private String password;

}
