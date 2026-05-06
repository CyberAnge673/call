package com.telecom.call.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telecom.call.DTO.response.ExtensionResponseDto;
import com.telecom.call.Repository.ExtensionRepo;
import com.telecom.call.Mapper.ExtensionMapper;

@Service
public class ExtensionService {

  @Autowired
  ExtensionRepo extensionRepo;

  public boolean existExtension(Long id) {
    return extensionRepo.existsById(id);
  }

  public List<ExtensionResponseDto> getExtension() {
    return extensionRepo.findAll()
        .stream()
        .map(ExtensionMapper::toExtension)
        .toList();
  }

}
