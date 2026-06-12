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
        try {
            log.info("procesando request");
            Extension entity = ExtensionMapper.toExtension(extensionRequestDto);

            extensionRepo.save(entity);
        } catch (ExtensionNotFoundException exception) {
            log.error("ocurrio error: {} " + exception.getMessage());
            throw exception;
        }
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
