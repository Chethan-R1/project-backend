package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.model.Vote;
import com.example.PlaningPoker.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VoteController {

    private final VoteService service;

    @GetMapping
    public List<Vote> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Vote getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Vote create(@RequestBody Vote vote) {
        return service.create(vote);
    }

    @PutMapping("/{id}")
    public Vote update(@PathVariable String id, @RequestBody Vote vote) {
        return service.update(id, vote);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

