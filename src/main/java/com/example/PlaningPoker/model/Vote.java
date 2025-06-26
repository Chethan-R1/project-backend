package com.example.PlaningPoker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vote") // MongoDB collection name
public class Vote {

    @Id
    private String voteId; // MongoDB uses String/ObjectId for IDs

    @DBRef
    private User user;

    @DBRef
    private Question question;

    @DBRef
    private String storyId;

    private String voteValue;

    private LocalDateTime votedAt = LocalDateTime.now();
}
