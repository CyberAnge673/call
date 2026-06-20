package com.telecom.call.mapper;

import com.telecom.call.dto.request.RolRequestDto;
import com.telecom.call.dto.response.RolResponseDto;
import com.telecom.call.enums.RolType;
import com.telecom.call.exception.RolInvaledException;
import com.telecom.call.model.Rol;
import java.util.Objects;
import org.apache.catalina.util.StringUtil;
import org.asteriskjava.manager.action.ExecAction;
import org.springframework.util.StringUtils;
import tools.jackson.databind.introspect.AnnotationCollector.TwoAnnotations;

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
            .rolname(rol.getName() != null ? rol.getName().toString() : null)
            .build();
    }

    public static Rol toRol(RolRequestDto reques) {
        if (
            reques == null ||
            reques.getName().isBlank() ||
            reques.getName() == null
        ) {
            return null;
        }
        RolType rolype = parseName(reques.getName());
        if (rolype == null) {
            throw new RolInvaledException("rol invalido: " + reques.getName());
        }
        return Rol.builder().name(rolype).build();
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
        } catch (IllegalArgumentException e) {
            throw new RolInvaledException("error en el proceso ", e);
        }
    }
}
