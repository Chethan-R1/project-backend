package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.User;
import com.example.PlaningPoker.model.Vote;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoteRepository extends MongoRepository<Vote, String> {
    void deleteByUserAndStoryId(User user, String storyId);

    List<Vote> findByStoryId(String storyId);

    void deleteByStoryId(String storyId);
}
