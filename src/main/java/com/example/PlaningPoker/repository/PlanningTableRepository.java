package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.PlanningTable;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanningTableRepository extends MongoRepository<PlanningTable, String> {
    List<PlanningTable> findByUser_Id(String userId);
}
