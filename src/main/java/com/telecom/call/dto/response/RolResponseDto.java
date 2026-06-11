package com.telecom.call.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * dto respuesta de rol al servidor
 *
 */

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolResponseDto {
  private Long rolId;
  private String rolname;
  private String descripcion;
  private Long userId;
  private List<UserResponseDto> users;

}
