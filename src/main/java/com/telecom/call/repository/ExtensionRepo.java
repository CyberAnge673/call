package com.telecom.call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.call.model.Extension;

@Repository
public interface ExtensionRepo extends JpaRepository<Extension, Long> {
}
