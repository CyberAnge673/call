package com.telecom.call.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * dto respuesta de las llamas
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneCallResponseDto {
  private Long callId;
  private String extension;
  private String origin;
  private String destination;
  private String context;
  private LocalDateTime start;
  private int duration;
  private String chanel;
  private int billsec;
  private Long userId;

}
