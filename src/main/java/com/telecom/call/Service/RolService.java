package com.telecom.call.Service;

import com.telecom.call.CallApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telecom.call.DTO.response.RolResponseDto;
import com.telecom.call.Mapper.Rolmapper;
import com.telecom.call.Repository.RolRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolService {
    private final CallApplication callApplication;
    @Autowired
    RolRepo rolRepo;

    RolService(CallApplication callApplication) {
        this.callApplication = callApplication;
    }

    public boolean existRol(Long id) {
        try {
            log.info("consulta realizada");
            boolean rol = rolRepo.existsById(id);
            log.info("rol existe");
            return rol;
        } catch (Exception e) {
            log.error("ocurio un erro:" + e);
            return false;
        }

    }

    public List<RolResponseDto> getRol() {
        try {
            log.info("Procesando roles");
            return rolRepo.findAll()
                    .stream()
                    .map(Rolmapper::toRol)
                    .toList();

        } catch (Exception e) {
            log.error("ocurio un error al consultar roles");
            return null;
        }

    }
}

