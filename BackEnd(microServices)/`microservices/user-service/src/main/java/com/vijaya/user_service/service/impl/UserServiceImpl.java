package com.vijaya.user_service.service.impl;

import com.vijaya.user_service.exception.UserException;
import com.vijaya.user_service.modal.User;
import com.vijaya.user_service.repository.UserRepository;
import com.vijaya.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vijaya.user_service.exception.UserException;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()) {
            return userId.get();
        }
        try {
            throw new Exception("User not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> userId = userRepository.findById(id);
        if(userId.isPresent()){
            userRepository.deleteById(id);

        }
        try {
            throw new Exception("User not found with id:"+id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(User user, Long id) {
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()) {
            User userDetails = userId.get();
            userDetails.setEmail(user.getEmail());
            userDetails.setFullName(user.getFullName());
            userDetails.setPhoneNumber(user.getPhoneNumber());
            userDetails.setRole(user.getRole());
            return userRepository.save(userDetails);
        }
        try {
            throw new UserException("User not found");
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }
}
