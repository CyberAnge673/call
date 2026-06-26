package com.telecom.call.mapper;

import com.telecom.call.dto.request.PhoneCallRequestDto;
import com.telecom.call.dto.response.PhoneCallResponseDto;
import com.telecom.call.enums.ContextType;
import com.telecom.call.model.Extension;
import com.telecom.call.model.PhoneCall;
import com.telecom.call.model.User;
import java.time.LocalDateTime;
import java.util.Optional;

/*
 *transforma las llamadas (phoneCall) a response
 */
public class PhoneCallMapper {

    private PhoneCallMapper() {
        throw new UnsupportedOperationException("no se pude instanciar");
    }

    public static PhoneCallResponseDto toPhoneCall(PhoneCall phoneCall) {
        if (phoneCall == null) {
            return null;
        }
        return PhoneCallResponseDto.builder()
            .callId(phoneCall.getId())
            .extension(phoneCall.getExtension() != null ? phoneCall.getExtension().getNumber() : null)
            .origin(phoneCall.getOrigin())
            .destination(phoneCall.getDestination())
            .context(phoneCall.getContext().toString())
            .start(phoneCall.getStart())
            .duration(phoneCall.getDuration())
            .channel(phoneCall.getChannel())
            .billsec(phoneCall.getBillsec())
            .userId(phoneCall.getUser() != null ? phoneCall.getUser().getId() : null)
            .build();
    }

    public static PhoneCall toPhoneCall(PhoneCallRequestDto phoneCalldto) {
        ContextType parsedContext = parseContextTypeSafe(phoneCalldto.getContextType());
        return PhoneCall.builder()
            .origin(phoneCalldto.getOrigin())
            .channel(phoneCalldto.getChannel())
            .context(parsedContext)
            .destination(phoneCalldto.getDestination())
            .extension(
                Extension.builder().id(phoneCalldto.getExtensionId()).build()
            )
            .duration(0)
            .billsec(0)
            .start(LocalDateTime.now())
            .user(User.builder().id(phoneCalldto.getUserId()).build())
            .build();
    }

    private static ContextType parseContextTypeSafe(String context) {
        if (context == null || context.isBlank()) {
            return ContextType.INTERNAL;
        }
        try {
            return ContextType.valueOf(context.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ContextType.INTERNAL;
        }
    }

    private Optional<ContextType> parseContext(String context) {
        if (context == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(ContextType.valueOf(context.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
