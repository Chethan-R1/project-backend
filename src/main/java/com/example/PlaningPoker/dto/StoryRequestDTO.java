package com.example.PlaningPoker.dto;

import lombok.Data;

@Data
public class StoryRequestDTO {
    private String title;
    private String description;
    private PlanTableDTO planTable;
    private CreatedByDTO createdBy;

    @Data
    public static class PlanTableDTO {
        private String id; // planning table ID
    }

    @Data
    public static class CreatedByDTO {
        private String userId; // creator's user ID
    }
}
