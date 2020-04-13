package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    List<UserEntity> findAllByType(UserEntity.UserType type);

    List<UserEntity> findFirstByTypeOrderByTicketsAssigned(UserEntity.UserType type);

    List<UserEntity> findFirst10ByUsernameContainingIgnoreCaseOrderByIdDesc(String usernameFragment);
}
