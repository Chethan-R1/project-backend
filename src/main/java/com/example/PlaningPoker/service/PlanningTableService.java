package com.example.PlaningPoker.service;

import com.example.PlaningPoker.model.PlanningTable;
import com.example.PlaningPoker.repository.PlanningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanningTableService {

    @Autowired
    private PlanningTableRepository repository;

    public List<PlanningTable> getAll() {
        return repository.findAll();
    }

    public PlanningTable getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planning table not found"));
    }

    public List<PlanningTable> getByUserId(String userId) {
        return repository.findByUser_Id(userId);
    }

    public PlanningTable create(PlanningTable table) {
        table.setCreatedAt(LocalDateTime.now());
        table.setModifiedAt(LocalDateTime.now());
        return repository.save(table);
    }

    public PlanningTable update(String id, PlanningTable updated) {
        PlanningTable table = getById(id);
        table.setTableName(updated.getTableName());
        table.setUser(updated.getUser());
        table.setOrganization(updated.getOrganization());
        table.setModifiedAt(LocalDateTime.now());
        return repository.save(table);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
