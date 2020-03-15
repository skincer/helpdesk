package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
