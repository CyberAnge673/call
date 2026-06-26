package com.telecom.call.mapper;

import com.telecom.call.dto.request.SipFriendRequestDto;
import com.telecom.call.dto.response.SipFriendResponseDto;
import com.telecom.call.enums.SipType;
import com.telecom.call.model.SipFriend;

public class SipFriendMapper {

    private SipFriendMapper() {
        throw new UnsupportedOperationException("no se puede instanciar");
    }

    public static SipFriendResponseDto toResponse(SipFriend entity) {
        if (entity == null) {
            return null;
        }

        return SipFriendResponseDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .secret(entity.getSecret())
            .context(entity.getContext())
            .host(entity.getHost())
            .ipAddr(entity.getIpAddr())
            .sipType(entity.getSipType() != null ? entity.getSipType().getValue() : null)
            .extensionId(entity.getExtension() != null ? entity.getExtension().getId() : null)
            .extensionNumber(entity.getExtension() != null ? entity.getExtension().getNumber() : null)
            .build();
    }

    public static SipFriend toEntity(SipFriendRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return SipFriend.builder()
            .name(dto.getName())
            .secret(dto.getSecret())
            .context(dto.getContext() != null ? dto.getContext() : "default")
            .host(dto.getHost() != null ? dto.getHost() : "dynamic")
            .ipAddr(dto.getIpAddr())
            .sipType(parseSipType(dto.getSipType()))
            .build();
    }

    public static SipType parseSipType(String sipType) {
        if (sipType == null || sipType.isBlank()) {
            return SipType.FRIEND;
        }
        try {
            return SipType.valueOf(sipType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return SipType.FRIEND;
        }
    }
}
