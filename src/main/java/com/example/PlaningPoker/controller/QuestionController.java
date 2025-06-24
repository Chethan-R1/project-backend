package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.model.Question;
import com.example.PlaningPoker.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService service;

    @GetMapping
    public List<Question> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Question create(@RequestBody Question question) {
        return service.create(question);
    }

    @PutMapping("/{id}")
    public Question update(@PathVariable Long id, @RequestBody Question question) {
        return service.update(id, question);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

