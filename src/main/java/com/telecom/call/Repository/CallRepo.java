package com.telecom.call.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telecom.call.Model.Call;

@Repository
public interface CallRepo extends JpaRepository<Call, Long> {
}
