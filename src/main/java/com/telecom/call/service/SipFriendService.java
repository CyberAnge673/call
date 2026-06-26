package com.telecom.call.service;

import com.telecom.call.dto.request.SipFriendRequestDto;
import com.telecom.call.dto.response.SipFriendResponseDto;
import com.telecom.call.exception.SipFriendNotFoundException;
import com.telecom.call.mapper.SipFriendMapper;
import com.telecom.call.model.Extension;
import com.telecom.call.model.SipFriend;
import com.telecom.call.repository.ExtensionRepo;
import com.telecom.call.repository.SipFriendRepo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SipFriendService {

    @Autowired
    private SipFriendRepo sipFriendRepo;

    @Autowired
    private ExtensionRepo extensionRepo;

    @Autowired
    private AsteriskService asteriskService;

    public SipFriendResponseDto save(SipFriendRequestDto dto) {
        try {
            SipFriend entity = SipFriendMapper.toEntity(dto);
            if (dto.getExtensionId() != null) {
                Extension ext = extensionRepo
                    .findById(dto.getExtensionId())
                    .orElse(null);
                entity.setExtension(ext);
            }
            SipFriend saved = sipFriendRepo.save(entity);
            asteriskService.reloadSip();
            log.info("SipFriend creado: {}", saved.getName());
            return SipFriendMapper.toResponse(saved);
        } catch (Exception e) {
            log.error("Error al guardar SipFriend: {}", e.getMessage());
            throw e;
        }
    }

    public SipFriendResponseDto update(Long id, SipFriendRequestDto dto) {
        SipFriend existing = sipFriendRepo
            .findById(id)
            .orElseThrow(() ->
                new SipFriendNotFoundException("SipFriend no encontrado con id: " + id)
            );

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getSecret() != null) {
            existing.setSecret(dto.getSecret());
        }
        if (dto.getContext() != null) {
            existing.setContext(dto.getContext());
        }
        if (dto.getHost() != null) {
            existing.setHost(dto.getHost());
        }
        if (dto.getIpAddr() != null) {
            existing.setIpAddr(dto.getIpAddr());
        }
        if (dto.getSipType() != null) {
            existing.setSipType(SipFriendMapper.parseSipType(dto.getSipType()));
        }
        if (dto.getExtensionId() != null) {
            Extension ext = extensionRepo
                .findById(dto.getExtensionId())
                .orElse(null);
            existing.setExtension(ext);
        }

        SipFriend saved = sipFriendRepo.save(existing);
        asteriskService.reloadSip();
        log.info("SipFriend actualizado: {}", saved.getName());
        return SipFriendMapper.toResponse(saved);
    }

    public void delete(Long id) {
        SipFriend entity = sipFriendRepo
            .findById(id)
            .orElseThrow(() ->
                new SipFriendNotFoundException("SipFriend no encontrado con id: " + id)
            );
        sipFriendRepo.delete(entity);
        asteriskService.reloadSip();
        log.info("SipFriend eliminado: {}", entity.getName());
    }

    public SipFriendResponseDto getById(Long id) {
        return sipFriendRepo
            .findById(id)
            .map(SipFriendMapper::toResponse)
            .orElseThrow(() ->
                new SipFriendNotFoundException("SipFriend no encontrado con id: " + id)
            );
    }

    public List<SipFriendResponseDto> getAll() {
        return sipFriendRepo
            .findAll()
            .stream()
            .map(SipFriendMapper::toResponse)
            .toList();
    }

    public boolean exists(Long id) {
        return sipFriendRepo.existsById(id);
    }
}
