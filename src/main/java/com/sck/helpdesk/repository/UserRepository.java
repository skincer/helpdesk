package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
