package com.example.shapesecurity.userService;

import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.example.shapesecurity.model.user.AuthenticationResponse;
import com.example.shapesecurity.model.user.Role;
import com.example.shapesecurity.model.user.User;
import com.example.shapesecurity.repository.UserRepository;
import com.example.shapesecurity.service.JwtService;
import com.example.shapesecurity.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtServiceImpl;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void testRegister_shouldSaveUser() {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Testowy", "test@gmail.com", "Testowe");

        userServiceImpl.register(createUserCommand);

        verify(passwordEncoder).encode(createUserCommand.getPassword());
        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();

        assertEquals(user.getFirstname(), createUserCommand.getFirstName());
        assertEquals(user.getEmail(), createUserCommand.getEmail());
        assertEquals(user.getLastname(), createUserCommand.getLastName());
        assertEquals(user.getRole(), Role.ROLE_USER);
    }

    @Test
    public void testAuthenticate() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@gmail.com", "test");
        User user = new User(1, "Test", "Testowy", authenticationRequest.getEmail(), authenticationRequest.getPassword(), Role.ROLE_CREATOR);
        String token = "generatedToken";

        when(jwtServiceImpl.generateToken(user)).thenReturn(token);
        when(userRepository.findByEmail(authenticationRequest.getEmail())).thenReturn(java.util.Optional.of(user));

        AuthenticationResponse response = userServiceImpl.authenticate(authenticationRequest);

        assertEquals(token, response.getToken());
    }


    @Test
    public void testGrantPermission_shouldAddRole() {
        User user = new User(1, "Test", "Test", "test@gmail.com", "Test", Role.ROLE_USER);
        Permission permission = new Permission("test@gmail.com", "CREATOR");

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));

        userServiceImpl.grantPermission(permission);

        assertEquals(Role.ROLE_CREATOR, user.getRole());
    }
}

