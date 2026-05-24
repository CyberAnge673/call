package com.telecom.call.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.call.DTO.request.PhoneCallRequestDto;
import com.telecom.call.DTO.response.PhoneCallResponseDto;
import com.telecom.call.Mapper.PhoneCallMapper;
import com.telecom.call.Repository.PhoneCallRepo;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PhoneCallService {

    @Autowired
    private PhoneCallRepo phoneCallRepo;

    public void savePhoneCall(PhoneCallRequestDto phoneCallRequestDto) {
        try {
            phoneCallRepo.save(PhoneCallMapper.toPhoneCall(phoneCallRequestDto));
            log.info("llamada guardada");
        } catch (Exception e) {
            // Manejar la excepción de manera adecuada, por ejemplo, registrándola o lanzando una excepción personalizada
            log.error("Error al guardar la llamada telefónica: {}", e.getMessage());
        }
    }
    
    public List<PhoneCallResponseDto> getAllPhoneCalls(){
        return phoneCallRepo.findAll()
        .stream()
        .map(PhoneCallMapper::toPhoneCall)
        .toList();

    }
    
    public void deletePhoneCall(Long id){
        try {
            if(!(phoneCallRepo.existsById(id))){
                phoneCallRepo.deleteById(id);
                throw new RuntimeException("la llamada no existe");
                
            }
            phoneCallRepo.deleteById(id);
            log.info("llamada eliminada");
        } catch (Exception e) { 
            log.error("Error al eliminar la llamada telefónica: {}", e.getMessage());
        }
    }
    

}
