package com.telecom.call.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SipFriendResponseDto {
    private Long id;
    private String name;
    private String secret;
    private String context;
    private String host;
    private String ipAddr;
    private String sipType;
    private Long extensionId;
    private String extensionNumber;
}
