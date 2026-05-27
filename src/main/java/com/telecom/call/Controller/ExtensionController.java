package com.telecom.call.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.call.DTO.request.ExtensionRequestDto;
import com.telecom.call.DTO.response.ExtensionResponseDto;
import com.telecom.call.Service.ExtensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/extension")
public class ExtensionController {
    @Autowired
    ExtensionService extensionService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ExtensionResponseDto> getMethodName(@RequestParam Long id) {
        if(!extensionService.existExtension(id)){
            return ResponseEntity.notFound().build();
        }
        ExtensionResponseDto extension = extensionService.getExtension(id);
        return ResponseEntity.ok(extension);

    }
    
}
