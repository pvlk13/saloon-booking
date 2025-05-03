package com.vijaya.user_service.controller;

import com.vijaya.user_service.exception.UserException;
import com.vijaya.user_service.modal.User;
import com.vijaya.user_service.repository.UserRepository;
import com.vijaya.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("api/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
      User createdUser = userService.createUser(user);
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers(){
      List<User> users = userService.getAllUsers();
      return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }
    @PutMapping("api/user/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable Long id) throws Exception {
       User userUpdated = userService.updateUser(user,id);
       return new ResponseEntity<>(userUpdated,HttpStatus.OK);
    }
    @DeleteMapping("api/user/{id}")
    public String deleteUserById(@PathVariable Long id) throws Exception {
      userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.OK).toString();

    }

}
