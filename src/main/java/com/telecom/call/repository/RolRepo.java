package com.telecom.call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.telecom.call.model.Rol;

@Service
public interface RolRepo extends JpaRepository<Rol, Long> {
}
