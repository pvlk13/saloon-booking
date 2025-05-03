package com.vijaya.user_service.service;

import com.vijaya.user_service.modal.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    User updateUser(User user, Long id);
}
