package com.telecom.call.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * dto respuesta Extension dto
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtensionResponseDto {
  private Long id;
  private String number;
  private String displayname;
  private String extensionType;
  private String contextType;
  private Long idUser;
  private LocalDateTime dateCreation;
  private String host;
  private String status;
  private LocalDateTime lastRegister;
}
