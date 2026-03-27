package com.telecom.call.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.call.Model.Extension;

@Repository
public interface ExtensionRepo extends JpaRepository<Extension, Long> {
}
