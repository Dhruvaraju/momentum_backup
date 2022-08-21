package com.alpha.momentum.repository;

import com.alpha.momentum.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserNameOrEmail(String userName, String email);
}
