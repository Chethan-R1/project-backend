package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.dto.VoteRequest;
import com.example.PlaningPoker.dto.VoteResponse;
import com.example.PlaningPoker.model.Vote;
import com.example.PlaningPoker.service.VoteService;
import com.example.PlaningPoker.service.VotingWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "http://localhost:4200")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VotingWebSocketHandler webSocketHandler;

    @PostMapping("/cast")
    public ResponseEntity<VoteResponse> castVote(@RequestBody VoteRequest request) {
        try {
            Vote vote = voteService.castVote(request);

            // The WebSocket broadcasting is handled in the WebSocket handler
            // when it receives the VOTE message from the client

            return ResponseEntity.ok(new VoteResponse(vote.getVoteId(), "Vote cast successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new VoteResponse(null, "Error casting vote: " + e.getMessage()));
        }
    }

    @PostMapping("/reveal/{storyId}")
    public ResponseEntity<List<Vote>> revealVotes(@PathVariable String storyId) {
        List<Vote> votes = voteService.getVotesForStory(storyId);
        return ResponseEntity.ok(votes);
    }

    @PostMapping("/reset/{storyId}")
    public ResponseEntity<String> resetVotes(@PathVariable String storyId) {
        voteService.resetVotesForStory(storyId);
        return ResponseEntity.ok("Votes reset successfully");
    }
}
