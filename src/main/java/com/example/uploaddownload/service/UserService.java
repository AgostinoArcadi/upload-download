package com.example.uploaddownload.service;

import com.example.uploaddownload.entity.User;
import com.example.uploaddownload.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        User userNuovo = userRepository.save(user);
        return userNuovo;
    }

    public Optional<User> findById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isPresent()) {
            return userOpt;
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(Long id, User user) {
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isPresent()) {
            userOpt.get().setName(user.getName());
            userOpt.get().setProfilePicture(user.getProfilePicture());

            User userUpdated = userRepository.save(userOpt.get());
            return Optional.of(userUpdated);
        }
        return Optional.empty();
    }

    public Optional<User> deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isPresent()) {
            userRepository.deleteById(id);
            return userOpt;
        }
        return Optional.empty();
    }

}
