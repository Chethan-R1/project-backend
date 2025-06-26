package com.example.PlaningPoker.dto;

import com.example.PlaningPoker.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    private User user;
    private String storyId;
    private String voteValue;


}
