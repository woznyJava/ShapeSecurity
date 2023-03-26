package com.example.shapesecurity.jwtService;

import com.example.shapesecurity.model.user.Role;
import com.example.shapesecurity.model.user.User;
import com.example.shapesecurity.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTests {
    @InjectMocks
    private JwtServiceImpl jwtServiceImpl;

    @Test
    public void shouldExtractName() {
        User user = new User(1, "test", "test", "testowy@gmail.com", "password", Role.ROLE_USER);
        String token = jwtServiceImpl.generateToken(user);

        String name = jwtServiceImpl.extractUserName(token);
        assertEquals(name, "testowy@gmail.com");
    }

    @Test
    public void testGenerateToken() {
        User user = new User(1, "test", "test", "test@gmail.com", "password", Role.ROLE_USER);
        String token = jwtServiceImpl.generateToken(user);
        assertTrue(token.length() > 0);
    }

    @Test
    public void shouldGenerateTokenAndCheckTokenValid() {
        User user = new User(1, "TEST", "Test", "test@gmail.com", "password", Role.ROLE_USER);
        String token = jwtServiceImpl.generateToken(user);
        assertNotNull(token);
        assertTrue(jwtServiceImpl.isTokenValid(token, user));
    }
}
