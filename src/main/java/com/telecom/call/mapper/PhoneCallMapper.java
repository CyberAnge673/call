package com.telecom.call.mapper;

import java.util.Optional;

import com.telecom.call.dto.request.PhoneCallRequestDto;
import com.telecom.call.dto.response.PhoneCallResponseDto;
import com.telecom.call.model.PhoneCall;
import com.telecom.call.enums.ContextType;

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
        .extension(phoneCall.getExtension().getNumber())
        .origin(phoneCall.getOrigin())
        .destination(phoneCall.getDestination())
        .context(phoneCall.getContext().toString())
        .start(phoneCall.getStart())
        .duration(phoneCall.getDuration())
        .chanel(phoneCall.getChannel())
        .billsec(phoneCall.getBillsec())
        .userId(phoneCall.getUser().getId())
        .build();
  }

  public static PhoneCall toPhoneCall(PhoneCallRequestDto phoneCalldto) {
    return null;
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
