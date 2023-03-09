package com.example.shapesecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketExceptionHandler extends AbstractWebSocketHandler {

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            sendMessage(session, errors);
        }
    }

    private void sendMessage(WebSocketSession session, Object message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(message);
        session.sendMessage(new TextMessage(jsonMessage));
    }
}