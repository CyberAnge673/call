package com.telecom.call.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SipFriendRequestDto {

    private Long id;

    @NotBlank(message = "nombre obligatorio")
    private String name;

    private String secret;
    private String context;
    private String host;
    private String ipAddr;
    private String sipType;
    private Long extensionId;
}
