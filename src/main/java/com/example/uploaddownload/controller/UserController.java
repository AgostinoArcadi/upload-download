package com.example.uploaddownload.controller;

import com.example.uploaddownload.entity.User;
import com.example.uploaddownload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userNuovo = userService.addUser(user);
        return ResponseEntity.ok().body(userNuovo);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOpt = userService.findById(id);

        if(userOpt.isPresent()) {
            return ResponseEntity.ok().body(userOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOpt = userService.updateUser(id, user);

        if(userOpt.isPresent()) {
            return ResponseEntity.ok().body(userOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOpt = userService.deleteUser(id);

        if(userOpt.isPresent()){
            return ResponseEntity.ok().body(userOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}

