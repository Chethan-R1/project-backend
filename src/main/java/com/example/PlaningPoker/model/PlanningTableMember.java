package com.example.PlaningPoker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "planning_table_member")
public class PlanningTableMember {

    @Id
    private String id;

    @DBRef
    private PlanningTable planTable;

    @DBRef
    private User user;

    private LocalDateTime joinedAt = LocalDateTime.now();
}
