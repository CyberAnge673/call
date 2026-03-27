package com.telecom.call.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.telecom.call.Model.User;

@Service
public interface UserRepo extends JpaRepository<User, Long> {
}
