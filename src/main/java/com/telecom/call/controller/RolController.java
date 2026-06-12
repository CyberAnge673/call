package com.telecom.call.controller;

import com.telecom.call.dto.response.RolResponseDto;
import com.telecom.call.service.RolService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rol")
@RequiredArgsConstructor
@Slf4j
public class RolController {

    private final RolService rolService;

    @GetMapping("/rol")
    public List<RolResponseDto> getRoles() {
        List<RolResponseDto> roles = rolService.getRol();
        return roles;
    }
}
