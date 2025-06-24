package com.example.PlaningPoker.service;

import com.example.PlaningPoker.model.PlanningTable;
import com.example.PlaningPoker.model.Story;
import com.example.PlaningPoker.repository.PlanningTableRepository;
import com.example.PlaningPoker.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepository repository;

    @Autowired
    private PlanningTableRepository planningTableRepository;

    public List<Story> getAll() {
        return repository.findAll();
    }

    public List<Story> getByRoomId(String planTableId) {
        return repository.findByPlanTableId(planTableId);
    }


    public Story getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
    }

    public Story create(Story story) {
//        if (story.getPlanTable() == null || story.getPlanTable().getId() == null) {
//            throw new IllegalArgumentException("Planning table is required.");
//        }

//        PlanningTable table = planningTableRepository.findById(story.getPlanTable().getId())
//                .orElseThrow(() -> new RuntimeException("Planning table not found."));
//
//        story.setPlanTable(table);
//        story.setCreatedAt(LocalDateTime.now());
//        story.setModifiedAt(LocalDateTime.now());
//        return repository.save(story);
        return  null;
    }

    public Story update(String id, Story updated) {
        Story story = getById(id);
        story.setTitle(updated.getTitle());
        story.setDescription(updated.getDescription());
        story.setStoryPoint(updated.getStoryPoint());
        story.setModifiedAt(LocalDateTime.now());
        return repository.save(story);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
