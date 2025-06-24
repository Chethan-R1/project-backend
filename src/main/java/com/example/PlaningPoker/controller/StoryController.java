package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.dto.StoryRequestDTO;
import com.example.PlaningPoker.model.PlanningTable;
import com.example.PlaningPoker.model.Story;
import com.example.PlaningPoker.model.User;
import com.example.PlaningPoker.repository.PlanningTableRepository;
import com.example.PlaningPoker.repository.StoryRepository;
import com.example.PlaningPoker.repository.UserRepository;
import com.example.PlaningPoker.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StoryController {

    private final StoryService service;

    @Autowired
    private PlanningTableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    @GetMapping
    public List<Story> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Story getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/planTable/{planTableId}")
    public List<Story> getByPlanningTableId(@PathVariable String planTableId) {
        return service.getByRoomId(planTableId);
    }


    @PostMapping
    public ResponseEntity<?> createStory(@RequestBody StoryRequestDTO dto) {
        System.out.println(dto);
        if (dto.getPlanTable() == null || dto.getPlanTable().getId() == null) {
            return ResponseEntity.badRequest().body("Missing planTable ID");
        }

        // Fetch the planning table
        PlanningTable planTable = tableRepository.findById(dto.getPlanTable().getId())
                .orElseThrow(() -> new RuntimeException("Planning table not found"));
    System.out.println(planTable);
        // Optional: check the creator is the owner of the planning table
        if (dto.getCreatedBy() != null && dto.getCreatedBy().getUserId() != null) {
            if (planTable.getUser() == null || !dto.getCreatedBy().getUserId().equals(planTable.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only the owner can create stories in this table.");
            }
        }

        // Create story
        Story story = new Story();
        story.setTitle(dto.getTitle());
        story.setDescription(dto.getDescription());
        story.setPlanTable(planTable);
        story.setCreatedAt(LocalDateTime.now());
        story.setModifiedAt(LocalDateTime.now());

        Story saved = storyRepository.save(story);
        return ResponseEntity.ok(saved);
    }




//    @PostMapping
//    public Story create(@RequestBody Story story) {
//        return service.create(story);
//    }

    @PutMapping("/{id}")
    public Story update(@PathVariable String id, @RequestBody Story story) {
        return service.update(id, story);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
