package com.example.PlaningPoker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enable a simple in-memory message broker and set the prefix for topic destinations
        config.enableSimpleBroker("/topic");
        // Set prefix for messages bound for methods annotated with @MessageMapping
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint for SockJS fallback
        registry.addEndpoint("/ws/voting")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }
}
