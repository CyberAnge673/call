package com.telecom.call.controller;

import com.telecom.call.dto.request.SipFriendRequestDto;
import com.telecom.call.dto.response.SipFriendResponseDto;
import com.telecom.call.service.SipFriendService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sipfriend")
public class SipFriendController {

    private final SipFriendService sipFriendService;

    @PostMapping
    public ResponseEntity<SipFriendResponseDto> create(
        @Valid @RequestBody SipFriendRequestDto dto
    ) {
        SipFriendResponseDto response = sipFriendService.save(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SipFriendResponseDto> update(
        @PathVariable Long id,
        @Valid @RequestBody SipFriendRequestDto dto
    ) {
        SipFriendResponseDto response = sipFriendService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sipFriendService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SipFriendResponseDto> getById(@PathVariable Long id) {
        SipFriendResponseDto response = sipFriendService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<SipFriendResponseDto> getAll() {
        return sipFriendService.getAll();
    }
}
