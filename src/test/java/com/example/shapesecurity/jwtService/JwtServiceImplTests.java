package com.example.shapesecurity.jwtService;

import com.example.shapesecurity.model.user.Role;
import com.example.shapesecurity.model.user.User;
import com.example.shapesecurity.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTests {

    @InjectMocks
    private JwtServiceImpl jwtServiceImpl;

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final String USERNAME = "testuser";

    @Test
    public void shouldGenerateToken() {
        User user = new User(1,USERNAME, "Test", "test@gmail.com", "password", Role.ROLE_USER);
        String token = jwtServiceImpl.generateToken(user);

        assertNotNull(token);
        assertTrue(jwtServiceImpl.isTokenValid(token, user));
    }
}
