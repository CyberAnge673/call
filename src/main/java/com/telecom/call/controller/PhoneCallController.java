package com.telecom.call.controller;

import com.telecom.call.dto.request.PhoneCallRequestDto;
import com.telecom.call.dto.response.PhoneCallResponseDto;
import com.telecom.call.service.PhoneCallService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/call")
@Slf4j
@RequiredArgsConstructor
public class PhoneCallController {

    private final PhoneCallService phoneCallService;

    @GetMapping
    public ResponseEntity<List<PhoneCallResponseDto>> getCalls() {
        log.info("mostrando llamadas");
        List<PhoneCallResponseDto> calls = phoneCallService.getAllPhoneCalls();
        return ResponseEntity.ok(calls);
    }

    @PostMapping
    public ResponseEntity<Void> saveCall(
        @RequestBody @Valid PhoneCallRequestDto dto
    ) {
        log.info("guardando llamada");
        phoneCallService.savePhoneCall(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
