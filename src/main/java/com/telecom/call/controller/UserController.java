package com.telecom.call.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.call.dto.request.UserCreateRequestDto;
import com.telecom.call.dto.response.UserResponseDto;
import com.telecom.call.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user") // ← Añadir esto
public class UserController {

  @Autowired
  private UserService userService;

  // GET /api/users
  @GetMapping
  public List<UserResponseDto> getUsers() {
    log.info("Obteniendo todos los usuarios");
    return userService.getAllUsers();
  }

  // GET /api/users/{id} → Obtener usuario por ID
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
    log.info("Buscando usuario con ID: {}", id);

    if (!userService.isUserExist(id)) {
      log.warn("Usuario con ID {} no existe", id);
      return ResponseEntity.notFound().build(); // ← Añadir return
    }

    UserResponseDto user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/AllUsers")
  public List<UserResponseDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/saveUser")
  public ResponseEntity<String> saveUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {
    log.info("guardando usuario: {}", userCreateRequestDto.getName());
    try {
      userService.userSave(userCreateRequestDto);
      return ResponseEntity.ok("usuario guardado");
    } catch (Exception e) {
      log.error("Error al guardar el usuario: {}", e.getMessage());
      return ResponseEntity.status(500).body("Error al guardar el usuario");
    }

  }
}
