package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.Question;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, Long> {
}
