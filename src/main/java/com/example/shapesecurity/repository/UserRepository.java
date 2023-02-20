package com.example.shapesecurity.repository;

import com.example.shapesecurity.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
//    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    Optional<User> findByEmail(String email);
}
