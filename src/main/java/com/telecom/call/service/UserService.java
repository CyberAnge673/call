package com.telecom.call.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telecom.call.dto.request.UserCreateRequestDto;
import com.telecom.call.dto.response.UserResponseDto;
import com.telecom.call.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import com.telecom.call.mapper.UserMapper;
import com.telecom.call.model.User;

/*
servicio para la entidad usuario, se encarga de la logica de negocio y de la comunicacion con el repositorio, ademas de manejar los errores que puedan ocurrir en el proceso
 */
@Slf4j
@Service
public class UserService {
  @Autowired
  private UserRepo userRepo;

  public void userSave(UserCreateRequestDto user) {
    try {
      User userEntity = UserMapper.toUser(user);
      userRepo.save(userEntity);
      log.info("entidad guardada con exito");
    } catch (Exception e) {
      log.error("ocurrio un error al guardar la entidad");
    }
  }

  public boolean isUserExist(Long userId) {
    try {
      log.info("consulta realizada");
      boolean user = userRepo.existsById(userId);
      log.info("usuario existe");
      return user;
    } catch (Exception e) {
      log.error("ocurio un erro:" + e);
      return false;
    }

  }

  public List<UserResponseDto> getAllUsers() {
    try {
      log.info("Procesando usarios");
      return userRepo.findAll()
          .stream()
          .map(UserMapper::toUserResponseDto)
          .toList();

    } catch (Exception e) {
      log.error("ocurio un error al consultar usuarios");
      return null;
    }

  }

  public UserResponseDto getUser(long userId) {
    try {
      log.info("procesando el id del usaurio");
      return userRepo.findById(userId)
          .map(UserMapper::toUserResponseDto)
          .orElse(null);

    } catch (Exception e) {
      log.error("ocurio un error en la solicitud ");
      return null;
    }
  }

}
