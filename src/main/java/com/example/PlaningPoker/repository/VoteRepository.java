package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.Vote;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
}
