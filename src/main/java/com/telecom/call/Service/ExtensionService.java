package com.telecom.call.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telecom.call.DTO.request.ExtensionRequestDto;
import com.telecom.call.DTO.response.ExtensionResponseDto;
import com.telecom.call.Exception.ExtensionNotFoundException;
import com.telecom.call.Repository.ExtensionRepo;
import com.telecom.call.Mapper.ExtensionMapper;
import com.telecom.call.Model.Extension;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExtensionService {

  @Autowired
  ExtensionRepo extensionRepo;

  public void saveExtension(Long id, ExtensionRequestDto extensiondto) {
    try {
      log.info("Procesando la peticion");
      if (!(existExtension(id))) {
        throw new ExtensionNotFoundException("no se encontro la extension");

      }
      Extension entity = ExtensionMapper.toExtension(extensiondto);
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

  public List<ExtensionResponseDto> getExtension() {
    return extensionRepo.findAll()
        .stream()
        .map(ExtensionMapper::toExtensionResponse)
        .toList();
  }

  public ExtensionResponseDto getExtension(Long id) {
    try {
      log.info("procesando la informacions");
      return extensionRepo.findById(id)
          .map(ExtensionMapper::toExtensionResponse)
          .orElse(null);
    } catch (Exception e) {
      log.error("ocurrio el error siguiente  " + e);
      return null;

    }
  }

}
