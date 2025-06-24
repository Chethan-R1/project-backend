package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.model.PlanningTable;
import com.example.PlaningPoker.model.User;
import com.example.PlaningPoker.repository.UserRepository;
import com.example.PlaningPoker.service.PlanningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanningTableController {

    private final PlanningTableService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<PlanningTable> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PlanningTable getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<PlanningTable> getByUser(@PathVariable String userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<PlanningTable> createTable(@RequestBody PlanningTable table) {
        if (table.getUser().getId() != null) {
            User user = userRepository.findById(table.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            table.setUser(user);
            PlanningTable saved = service.create(table);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public PlanningTable update(@PathVariable String id, @RequestBody PlanningTable table) {
        return service.update(id, table);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
