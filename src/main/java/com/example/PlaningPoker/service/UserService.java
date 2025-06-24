package com.example.PlaningPoker.service;

import com.example.PlaningPoker.model.User;
import com.example.PlaningPoker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.findByName(user.getName())
                .orElseGet(() -> {
                    user.setCreatedAt(LocalDateTime.now());
                    user.setModifiedAt(LocalDateTime.now());
                    return userRepository.save(user);
                });
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getUsersByPlanningTable(String planTableId) {
        return userRepository.findUsersByPlanningTableId(planTableId);
    }

    public User getById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(String userId, User updated) {
        User user = getById(userId);
        user.setName(updated.getName());
        user.setEmail(updated.getEmail());
        user.setModifiedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void delete(String userId) {
        userRepository.deleteById(userId);
    }
}
