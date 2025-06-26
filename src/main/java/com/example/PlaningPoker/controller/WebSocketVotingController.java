package com.example.PlaningPoker.controller;

import com.example.PlaningPoker.dto.VoteRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketVotingController {


    @MessageMapping("/room/{roomId}") // Receives from: /app/room/{roomId}
    @SendTo("/topic/room/{roomId}")   // Sends to: /topic/room/{roomId}
    public VoteRequest handleMessage(VoteRequest message) {

        return message; // Echo back to subscribers
    }
}
