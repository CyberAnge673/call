package com.telecom.call.service;

import com.telecom.call.dto.request.ExtensionRequestDto;
import com.telecom.call.dto.response.ExtensionResponseDto;
import com.telecom.call.exception.ExtensionNotFoundException;
import com.telecom.call.mapper.ExtensionMapper;
import com.telecom.call.model.Extension;
import com.telecom.call.model.User;
import com.telecom.call.repository.ExtensionRepo;
import com.telecom.call.repository.UserRepo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExtensionService {

    @Autowired
    ExtensionRepo extensionRepo;

    @Autowired
    UserRepo userRepo;

    public void saveExtension(ExtensionRequestDto extensiondto) {
        try {
            log.info("Procesando la peticion");
            Extension entity = ExtensionMapper.toExtension(extensiondto);
            if (extensiondto.getUserId() != null) {
                User user = userRepo
                    .findById(extensiondto.getUserId())
                    .orElse(null);
                entity.setUser(user);
            }
            extensionRepo.save(entity);
        } catch (ExtensionNotFoundException exception) {
            log.error("ocurrio {}" + exception.getMessage());
            throw exception;
        }
    }

    public void updateExtension(ExtensionRequestDto extensionRequestDto) {
        if (extensionRequestDto.getId() == null) {
            log.error("id de extension no proporcionado para actualizar");
            throw new IllegalArgumentException("id es requerido para actualizar");
        }

        Extension existing = extensionRepo
            .findById(extensionRequestDto.getId())
            .orElseThrow(() -> new ExtensionNotFoundException("extension no encontrada con id: " + extensionRequestDto.getId()));

        if (extensionRequestDto.getNumber() != null) {
            existing.setNumber(extensionRequestDto.getNumber());
        }
        if (extensionRequestDto.getPassword() != null) {
            existing.setPassword(extensionRequestDto.getPassword());
        }
        if (extensionRequestDto.getDisplayname() != null) {
            existing.setDisplayname(extensionRequestDto.getDisplayname());
        }
        if (extensionRequestDto.getHost() != null) {
            existing.setHost(extensionRequestDto.getHost());
        }
        if (extensionRequestDto.getStatus() != null) {
            existing.setStatus(ExtensionMapper.parseStatusType(extensionRequestDto.getStatus()));
        }
        if (extensionRequestDto.getExtensionType() != null) {
            existing.setExtensionType(ExtensionMapper.parseExtensionTypeEnum(extensionRequestDto.getExtensionType()));
        }
        if (extensionRequestDto.getContextType() != null) {
            existing.setContextType(ExtensionMapper.parseContextType(extensionRequestDto.getContextType()));
        }
        if (extensionRequestDto.getUserId() != null) {
            User user = userRepo
                .findById(extensionRequestDto.getUserId())
                .orElse(null);
            existing.setUser(user);
        }

        extensionRepo.save(existing);
        log.info("extension actualizada correctamente");
    }

    public boolean existExtension(Long id) {
        return extensionRepo.existsById(id);
    }

    public List<ExtensionResponseDto> getExtensions() {
        return extensionRepo
            .findAll()
            .stream()
            .map(ExtensionMapper::toExtensionResponse)
            .toList();
    }

    public ExtensionResponseDto getExtension(Long id) {
        try {
            log.info("procesando la informacions");
            return extensionRepo
                .findById(id)
                .map(ExtensionMapper::toExtensionResponse)
                .orElse(null);
        } catch (Exception e) {
            log.error("ocurrio el error siguiente  " + e);
            return null;
        }
    }
}
