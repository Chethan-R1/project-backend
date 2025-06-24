package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.Story;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoryRepository extends MongoRepository<Story, String> {
    List<Story> findByPlanTableId(String planTableId);
}
