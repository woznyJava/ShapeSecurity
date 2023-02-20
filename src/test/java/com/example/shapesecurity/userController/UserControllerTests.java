package com.example.shapesecurity.userController;

import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.example.shapesecurity.model.user.Role;
import com.example.shapesecurity.model.user.User;
import com.example.shapesecurity.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testShouldRegisterUser() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");
        String payload = token.split("\\.")[1];
        Base64.Decoder base64 = Base64.getDecoder();
        Map<String, String> payloadData = objectMapper.readValue(base64.decode(payload), Map.class);
        assertEquals(payloadData.get("sub"), "test@gmail.com");
        assertEquals(payloadData.get("roles"), "[ROLE_USER]");
    }

    // wstrzyknac repo i w grantPermission assercje zrobic
    // na wejsciu po dodaniu ze ma usera

    //na koncu ze ma creatora
    //asserty dorobic
//json node albo json object


    @Test
    public void testShouldAuthenticateUser() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));


        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(authenticationRequest);

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");
        String payload = token.split("\\.")[1];
        Base64.Decoder base64 = Base64.getDecoder();
        Map<String, String> payloadData = objectMapper.readValue(base64.decode(payload), Map.class);
        assertEquals(payloadData.get("sub"), "test@gmail.com");
        assertEquals(payloadData.get("roles"), "[ROLE_USER]");
    }

    @Test
    @Transactional
    public void testShouldAddRole() throws Exception {

        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        User userBeforeUpdateRole = userRepository.findByEmail(createUserCommand.getEmail()).orElseThrow(() -> new EntityNotFoundException(
                "User has not been found"));

        assertEquals(userBeforeUpdateRole.getEmail(), "test@gmail.com");
        assertEquals(userBeforeUpdateRole.getRole(), Role.ROLE_USER);


        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(permission);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());

        User userAfterUpdateRole = userRepository.findByEmail(createUserCommand.getEmail()).orElseThrow(() -> new EntityNotFoundException(
                "User has not been found"));

        assertEquals(userAfterUpdateRole.getEmail(), "test@gmail.com");
        assertEquals(userAfterUpdateRole.getRole(), Role.ROLE_CREATOR);
    }
}
