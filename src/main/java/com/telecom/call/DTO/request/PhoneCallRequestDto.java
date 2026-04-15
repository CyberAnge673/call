package com.telecom.call.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCallRequestDto {
  @NotBlank(message = "dato de origen obligatorio")
  private String origin;
  @NotBlank(message = "dato de destination obligatorio")
  private String destination;
  @NotBlank(message = "el tipo de contexto obligatorio")
  private String contexType;
  @NotBlank(message = "canal obligatorio")
  private String chanel;
  @NotBlank(message = "usario id obligatorio")
  private Long userId;
  @NotBlank(message = "extensionId obligatorio")
  private Long extensionId;
}
