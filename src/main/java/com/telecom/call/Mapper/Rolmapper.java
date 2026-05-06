package com.telecom.call.Mapper;

import com.telecom.call.DTO.request.RolRequestDto;
import com.telecom.call.DTO.response.RolResponseDto;
import com.telecom.call.Enums.RolType;
import com.telecom.call.Model.Rol;

/*
 * mapper para rol transforma el rol a response y de response a rol
*/
public class Rolmapper {
  private Rolmapper() {
    throw new UnsupportedOperationException("no se puede instanciar");
  }

  public static RolResponseDto toRol(Rol rol) {
    if (rol == null) {
      return null;
    }
    return RolResponseDto.builder()
        .rolId(rol.getId())
        .rolname(parseName(rol.getName()))
        .build();
  }

  public static Rol toRol(RolRequestDto name) {
    if (name == null || name.getName().isBlank()) {
      return null;
    }
    return Rol.builder()
        .name(parseName(name.getName()))
        .build();
  }

  private static RolType parseName(String name) {
    if (name == null || name.isBlank()) {
      return null;
    }
    return RolType.valueOf(name.toUpperCase());
  }

  private static String parseName(RolType rol) {
    if (rol == null) {
      return null;
    }
    try {
      return rol.toString().toUpperCase();
    } catch (Exception e) {
      return null;
    }
  }
}
