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
public class RolRequestDto {
  @NotBlank(message = "nombre requerido")
  private String name;
  @NotBlank(message = "Descripcion requerida")
  private String Descripcion;
  @NotBlank(message = "id del usuario requerido")
  private long userId;

}
