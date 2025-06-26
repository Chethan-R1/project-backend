package com.example.PlaningPoker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VotingWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, Set<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();
    private final Map<String, String> sessionToRoom = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            Map<String, Object> payload = objectMapper.readValue(message.getPayload(), Map.class);
            String action = (String) payload.get("action");
            String roomId = (String) payload.get("roomId");

            switch (action) {
                case "JOIN_ROOM":
                    joinRoom(session, roomId);
                    break;
                case "VOTE":
                    handleVote(session, payload);
                    break;
                case "REVEAL_VOTES":
                    revealVotes(roomId);
                    break;
                case "RESET_VOTES":
                    resetVotes(roomId);
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = sessionToRoom.get(session.getId());
        if (roomId != null) {
            leaveRoom(session, roomId);
        }
    }

    private void joinRoom(WebSocketSession session, String roomId) {
        roomSessions.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(session);
        sessionToRoom.put(session.getId(), roomId);

        // Notify room about new participant
        broadcastToRoom(roomId, Map.of(
                "type", "USER_JOINED",
                "sessionId", session.getId(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    private void leaveRoom(WebSocketSession session, String roomId) {
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                roomSessions.remove(roomId);
            }
        }
        sessionToRoom.remove(session.getId());

        // Notify room about participant leaving
        broadcastToRoom(roomId, Map.of(
                "type", "USER_LEFT",
                "sessionId", session.getId(),
                "timestamp", System.currentTimeMillis()
        ));
    }

    private void handleVote(WebSocketSession session, Map<String, Object> payload) {
        String roomId = (String) payload.get("roomId");
        String vote = (String) payload.get("vote");
        String userId = (String) payload.get("userId");
        String storyId = (String) payload.get("storyId");

        // Broadcast vote update to all participants in the room
        broadcastToRoom(roomId, Map.of(
                "type", "VOTE_CAST",
                "userId", userId,
                "storyId", storyId,
                "hasVoted", true, // Don't reveal actual vote until reveal
                "timestamp", System.currentTimeMillis()
        ));
    }

    private void revealVotes(String roomId) {
        broadcastToRoom(roomId, Map.of(
                "type", "VOTES_REVEALED",
                "timestamp", System.currentTimeMillis()
        ));
    }

    private void resetVotes(String roomId) {
        broadcastToRoom(roomId, Map.of(
                "type", "VOTES_RESET",
                "timestamp", System.currentTimeMillis()
        ));
    }

    private void broadcastToRoom(String roomId, Map<String, Object> message) {
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            String messageJson;
            try {
                messageJson = objectMapper.writeValueAsString(message);
            } catch (Exception e) {
                System.err.println("Error serializing message: " + e.getMessage());
                return;
            }

            sessions.removeIf(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(messageJson));
                        return false;
                    }
                } catch (Exception e) {
                    System.err.println("Error sending message to session: " + e.getMessage());
                }
                return true; // Remove closed sessions
            });
        }
    }
}
