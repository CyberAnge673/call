package com.telecom.call.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.telecom.call.Model.Rol;

@Service
public interface RolRepo extends JpaRepository<Rol, Long> {
}
