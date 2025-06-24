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
@Document(collection = "question")
public class Question {

    @Id
    private String questionId;  // MongoDB typically uses String IDs

    @DBRef
    private PlanningTable planTable;

    @DBRef
    private Story story;

    private String questionText;
    private Boolean isActive;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
