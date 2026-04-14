package com.telecom.call.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telecom.call.Model.PhoneCall;

@Repository
public interface PhoneCallRepo extends JpaRepository<PhoneCall, Long> {
}
