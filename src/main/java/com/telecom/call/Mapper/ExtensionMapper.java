package com.telecom.call.Mapper;

import com.telecom.call.Model.Extension;

import com.telecom.call.DTO.response.ExtensionResponseDto;
import com.telecom.call.Enums.ExtensionType;
import com.telecom.call.Enums.StatusType;
import com.telecom.call.DTO.request.ExtensionRequestDto;
import java.util.Optional;
public class ExtensionMapper {

  private ExtensionMapper(){
    throw new UnsupportedOperationException("esta clase no se puede instanciar");
  }

  public static ExtensionResponseDto toExtension(Extension extension) {
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
  public static Extension toExtension(ExtensionRequestDto extensiondto){
    
    if(extensiondto == null){
      return null;
    }
    return Extension.builder()
    .number(extensiondto.getNumber())
    .password(extensiondto.getPassword())
    .status(parseStatus(extensiondto.getStatus()).orElse(null))
    .host(extensiondto.getHost())
    .extensionType(parseExtensionType(extensiondto.getExtensionType()).orElse(null))
    .contextType(null)
    .build();
  }

  private static Optional<StatusType> parseStatus(String status){
    if(status == null || status.isBlank()){
      return Optional.empty();
    }
    try{
      return Optional.of(StatusType.valueOf(status.toUpperCase()));

    }catch(IllegalArgumentException e){
      return Optional.empty();
    }
  }

  private static Optional<ExtensionType> parseExtensionType(String extensiontype){
    if(extensiontype == null || extensiontype.isBlank()){
      return Optional.empty();
    }
    try {
      return Optional.of(ExtensionType.valueOf(extensiontype.toUpperCase()));
    } catch (IllegalArgumentException exception) {
      return Optional.empty();
    }
  }
}



