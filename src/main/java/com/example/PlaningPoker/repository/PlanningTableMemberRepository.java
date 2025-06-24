package com.example.PlaningPoker.repository;

import com.example.PlaningPoker.model.PlanningTableMember;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanningTableMemberRepository extends MongoRepository<PlanningTableMember, String> {
    List<PlanningTableMember> findByPlanTableId(String planTableId);
    void deleteByPlanTableIdAndUserId(String planTableId, String userId);
    boolean existsByUser_IdAndPlanTable_Id(String userId, String tableId);
}

