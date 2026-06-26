package com.telecom.call.repository;

import com.telecom.call.model.SipFriend;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SipFriendRepo extends JpaRepository<SipFriend, Long> {
    Optional<SipFriend> findByName(String name);
}
