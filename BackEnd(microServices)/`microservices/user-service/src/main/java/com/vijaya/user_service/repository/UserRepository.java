package com.vijaya.user_service.repository;

import com.vijaya.user_service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
