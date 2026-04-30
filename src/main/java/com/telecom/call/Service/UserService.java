package com.telecom.call.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.call.DTO.response.UserResponseDto;
import com.telecom.call.Repository.UserRepo;
import com.telecom.call.Mapper.UserMapper;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public boolean isUserExist(Long userId){
        return userRepo.existsById(userId);
    }
    
    public List<UserResponseDto> getAllUsers(){
        return userRepo.findAll()
        .stream()
        .map(UserMapper::toUser)
        .toList();
        
    }

    public UserResponseDto getUser(long userId){
        return userRepo.findById(userId)
        .map(UserMapper::toUser)
        .orElse(null);
    }

    
}
