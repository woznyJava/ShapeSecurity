package com.example.shapesecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/my-websocket-endpoint")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new TextWebSocketHandler() {
            @Override
            public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

            }
        };
    }
//    @Bean
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new WebSocketHandlerExceptionResolver();
//    }
//
//    @Bean
//    public DefaultHandshakeHandler handshakeHandler() {
//        return new DefaultHandshakeHandler();
//    }
//
//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//        registration.addDecoratorFactory(webSocketHandlerDecoratorFactory());
//    }
//
//    @Bean
//    public WebSocketHandlerDecoratorFactory webSocketHandlerDecoratorFactory() {
//        return new WebSocketHandlerDecoratorFactory() {
//            @Override
//            public WebSocketHandler decorate(WebSocketHandler handler) {
//                return new ExceptionWebSocketHandlerDecorator(new ExceptionHandlingWebSocketHandler(handler), handlerExceptionResolver());
//            }
//        };
//    }
}


