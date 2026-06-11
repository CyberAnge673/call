package com.telecom.call.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.call.dto.request.ExtensionRequestDto;
import com.telecom.call.dto.response.ExtensionResponseDto;
import com.telecom.call.service.ExtensionService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/api/extension")
public class ExtensionController {
  @Autowired
  ExtensionService extensionService;

  @GetMapping("/{id}")
  public ResponseEntity<ExtensionResponseDto> getMethodName(@RequestParam Long id) {
    if (!extensionService.existExtension(id)) {
      return ResponseEntity.notFound().build();
    }
    ExtensionResponseDto extension = extensionService.getExtension(id);
    return ResponseEntity.ok(extension);

  }

  @GetMapping("/extensions")
  public List<ExtensionResponseDto> getMethodName() {
    try {
      return extensionService.getExtensions();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/extensions")
  public ResponseEntity<String> saveExtension(@RequestBody ExtensionRequestDto extensionRequestDto) {
    try {
      log.info("guardado extension");
      extensionService.saveExtension(extensionRequestDto);
      return ResponseEntity.ok("guardado correctamente");

    } catch (Exception e) {
      log.error("ocurrio el siguienre error  {}" + e);
      return ResponseEntity.status(500).body("error 500");
    }
  }

}
