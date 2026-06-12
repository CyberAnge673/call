package com.telecom.call.mapper;

import com.telecom.call.dto.request.ExtensionRequestDto;
import com.telecom.call.dto.response.ExtensionResponseDto;
import com.telecom.call.enums.ContextType;
import com.telecom.call.enums.ExtensionType;
import com.telecom.call.enums.StatusType;
import com.telecom.call.model.Extension;
import java.util.Optional;

public class ExtensionMapper {

    private ExtensionMapper() {
        throw new UnsupportedOperationException(
            "esta clase no se puede instanciar"
        );
    }

    public static ExtensionResponseDto toExtensionResponse(
        Extension extension
    ) {
        if (extension == null) {
            return null;
        }

        return ExtensionResponseDto.builder()
            .id(extension.getId())
            .number(extension.getNumber())
            .idUser(extension.getUser().getId())
            .displayname(extension.getDisplayname())
            .extensionType(extension.getExtensionType().toString())
            .contextType(extension.getContextType().toString())
            .host(extension.getHost())
            .status(extension.getStatus().toString())
            .dateCreation(extension.getCreation_date())
            .lastRegister(extension.getLastRegister())
            .build();
    }

    public static Extension toExtension(ExtensionRequestDto extensiondto) {
        if (extensiondto == null) {
            return null;
        }
        return Extension.builder()
            .number(extensiondto.getNumber())
            .password(extensiondto.getPassword())
            .displayname(extensiondto.getDisplayname())
            .host(extensiondto.getHost())
            .status(parseStatusType(extensiondto.getStatus()))
            .extensionType(
                parseExtensionTypeEnum(extensiondto.getExtensionType())
            )
            .contextType(parseContextType(extensiondto.getContextType()))
            .build();
    }

    private static Optional<StatusType> parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(StatusType.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private static Optional<ExtensionType> parseExtensionType(
        String extensiontype
    ) {
        if (extensiontype == null || extensiontype.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(
                ExtensionType.valueOf(extensiontype.toUpperCase())
            );
        } catch (IllegalArgumentException exception) {
            return Optional.empty();
        }
    }

    public static StatusType parseStatusType(String status) {
        return parseStatus(status).orElse(StatusType.INACTIVE);
    }

    public static ExtensionType parseExtensionTypeEnum(String extensionType) {
        return parseExtensionType(extensionType).orElse(ExtensionType.SIP);
    }

    public static ContextType parseContextType(String contextType) {
        if (contextType == null || contextType.isBlank()) {
            return ContextType.INTERNAL;
        }
        try {
            return ContextType.valueOf(contextType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ContextType.INTERNAL;
        }
    }
}
