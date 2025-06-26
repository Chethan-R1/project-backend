package com.example.PlaningPoker.service;

import com.example.PlaningPoker.dto.VoteRequest;
import com.example.PlaningPoker.model.Vote;
import com.example.PlaningPoker.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    public List<Vote> getAll() {
        return repository.findAll();
    }

    public Vote getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vote not found"));
    }

    public Vote create(Vote vote) {
        vote.setVotedAt(LocalDateTime.now());
        return repository.save(vote);
    }

    public Vote update(String id, Vote updated) {
        Vote vote = getById(id);
        vote.setVoteValue(updated.getVoteValue());
        return repository.save(vote);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }



    public Vote castVote(VoteRequest request) {
        // Remove existing vote by same user for same story
        repository.deleteByUserAndStoryId(request.getUser(), request.getStoryId());

        Vote vote = new Vote();
        vote.setUser(request.getUser());
        vote.setStoryId(request.getStoryId());
        vote.setVoteValue(request.getVoteValue());
        vote.setVotedAt(LocalDateTime.now());

        return repository.save(vote);
    }

    public List<Vote> getVotesForStory(String storyId) {
        return repository.findByStoryId(storyId);
    }

    public void resetVotesForStory(String storyId) {
        repository.deleteByStoryId(storyId);
    }
}

