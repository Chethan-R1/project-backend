package com.example.PlaningPoker.service;

import com.example.PlaningPoker.model.PlanningTableMember;
import com.example.PlaningPoker.repository.PlanningTableMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanningTableMemberService {
    @Autowired
    private PlanningTableMemberRepository repository;

    public List<PlanningTableMember> getAll() {
        return repository.findAll();
    }

    public PlanningTableMember create(PlanningTableMember member) {
        member.setJoinedAt(LocalDateTime.now());
        return repository.save(member);
    }

    public void delete(String planTableId, String userId) {
        repository.deleteByPlanTableIdAndUserId(planTableId, userId);
    }

    public List<PlanningTableMember> getByPlanTableId(String planTableId) {
        return repository.findByPlanTableId(planTableId);
    }
}

