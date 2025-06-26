package com.example.PlaningPoker.dto;

public class VoteResponse {

    private String voteId;
    private String message;

    public VoteResponse(String voteId, String message) {
        this.voteId = voteId;
        this.message = message;
    }

    public String getVoteId() { return voteId; }
    public String getMessage() { return message; }
}
