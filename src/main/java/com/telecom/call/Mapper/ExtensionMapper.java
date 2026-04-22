package com.telecom.call.Mapper;

import com.telecom.call.Model.Extension;

import com.telecom.call.DTO.response.ExtensionResponseDto;

public class ExtensionMapper {

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

}
