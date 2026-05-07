package com.telecom.call.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.call.DTO.response.UserResponseDto;
import com.telecom.call.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users") // ← Añadir esto
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
}
