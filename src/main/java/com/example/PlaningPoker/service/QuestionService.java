package com.example.PlaningPoker.service;

import com.example.PlaningPoker.model.Question;
import com.example.PlaningPoker.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repository;

    public List<Question> getAll() {
        return repository.findAll();
    }

    public Question getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public Question create(Question question) {
        question.setCreatedAt(LocalDateTime.now());
        question.setModifiedAt(LocalDateTime.now());
        return repository.save(question);
    }

    public Question update(Long id, Question updated) {
        Question question = getById(id);
        question.setQuestionText(updated.getQuestionText());
        question.setIsActive(updated.getIsActive());
        question.setModifiedAt(LocalDateTime.now());
        return repository.save(question);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
