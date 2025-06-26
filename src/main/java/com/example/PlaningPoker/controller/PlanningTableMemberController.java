package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.model.PlanningTable;
import com.example.PlaningPoker.model.PlanningTableMember;
import com.example.PlaningPoker.model.User;
import com.example.PlaningPoker.repository.PlanningTableMemberRepository;
import com.example.PlaningPoker.repository.PlanningTableRepository;
import com.example.PlaningPoker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanningTableMemberController {

    private final PlanningTableMemberRepository memberRepo;
    private final PlanningTableRepository tableRepo;
    private final UserRepository userRepo;

    @GetMapping("/users/{tableId}")
    public List<User> getUsersByPlanningTable(@PathVariable String tableId) {
        List<PlanningTableMember> members = memberRepo.findByPlanTableId(tableId);
        return members.stream()
                .map(PlanningTableMember::getUser)
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody Map<String, String> request) {
        String tableId = request.get("tableId");
        String userId = request.get("userId");

        boolean exists = memberRepo.existsByUser_IdAndPlanTable_Id(userId, tableId);
        if (exists) {
            return ResponseEntity.ok("User already in table");
        }

        PlanningTable table = tableRepo.findById(tableId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();

        PlanningTableMember member = new PlanningTableMember();
        member.setPlanTable(table);
        member.setUser(user);
        member.setJoinedAt(LocalDateTime.now());

        return ResponseEntity.ok(memberRepo.save(member));
    }

    @GetMapping
    public List<PlanningTableMember> getAll() {
        return memberRepo.findAll();
    }

    @DeleteMapping("/{tableId}/{userId}")
    public ResponseEntity<?> removeUserFromTable(@PathVariable String tableId, @PathVariable String userId) {
        Optional<PlanningTableMember> memberOpt = memberRepo.findByPlanTableIdAndUserId(tableId, userId);
        if (memberOpt.isPresent()) {
            memberRepo.delete(memberOpt.get());
            return ResponseEntity.ok("User removed from table");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
