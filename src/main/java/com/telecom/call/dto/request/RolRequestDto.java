package com.telecom.call.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String descripcion;

    @NotNull(message = "id del usuario requerido")
    private long userId;
}
