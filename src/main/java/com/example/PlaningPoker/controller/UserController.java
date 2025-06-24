package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.model.User;
import com.example.PlaningPoker.repository.UserRepository;
import com.example.PlaningPoker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/room/{planTableId}")
    public List<User> getUsersByRoom(@PathVariable String planTableId) {
        return service.getUsersByPlanningTable(planTableId);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        return userRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public User create(@RequestBody User user) {
        System.out.println(user);
        if (user.getId() != null && user.getId().trim().isEmpty()) {
            user.setId(null); // force ID regeneration
        }
        return service.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
