package com.telecom.call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.telecom.call.model.User;

@Service
public interface UserRepo extends JpaRepository<User, Long> {
}
