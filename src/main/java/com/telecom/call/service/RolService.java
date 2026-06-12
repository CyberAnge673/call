package com.telecom.call.service;

import com.telecom.call.dto.response.RolResponseDto;
import com.telecom.call.mapper.Rolmapper;
import com.telecom.call.repository.RolRepo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolService {

    @Autowired
    RolRepo rolRepo;

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
            return rolRepo.findAll().stream().map(Rolmapper::toRol).toList();
        } catch (Exception e) {
            log.error("ocurio un error al consultar roles");
            return null;
        }
    }
}
