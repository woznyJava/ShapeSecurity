package com.example.shapesecurity.shapeController;

import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@PrepareForTest
public class ShapeControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void testShouldThrowException_PARAMETERS_NOT_EMPTY() throws  Exception{

        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        String type = "CIRCLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, null);


        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);


        String token = savedToken.get("token");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Validation Failed"))
                .andExpect(jsonPath("$.details[0]").value("PARAMETERS_NOT_EMPTY"));
    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void testShouldThrowException_TYPE_NOT_EMPTY() throws  Exception{

        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        map.put("radius", 2.0);
        String type = "CIRCLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(null, map);


        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);


        String token = savedToken.get("token");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Validation Failed"))
                .andExpect(jsonPath("$.details[0]").value("TYPE_NOT_EMPTY"));
    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void testShouldSaveCircle() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        map.put("radius", 2.0);
        String type = "CIRCLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);


        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);


        String token = savedToken.get("token");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(2.0))
                .andExpect(jsonPath("$.area").value(12.56))
                .andExpect(jsonPath("$.perimeter").value(12.56))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void testShouldSaveRectangle() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        map.put("width", 4.0);
        map.put("height", 5.0);
        String type = "RECTANGLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.width").value(4.0))
                .andExpect(jsonPath("$.height").value(5.0))
                .andExpect(jsonPath("$.area").value(20.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));
    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void testShouldSaveSquare() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        map.put("side", 3.0);
        String type = "SQUARE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.side").value(3.0))
                .andExpect(jsonPath("$.area").value(9.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));
    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void shouldFilterShapes_RECTANGLE() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        Map<String, Double> map2 = new HashMap<>();
        Map<String, Double> map3 = new HashMap<>();
        Map<String, Double> map4 = new HashMap<>();
        map.put("radius", 2.0);
        map2.put("side", 3.0);
        map3.put("width", 4.0);
        map3.put("height", 5.0);
        map4.put("radius", 7.0);
        String type = "CIRCLE";
        String type2 = "SQUARE";
        String type3 = "RECTANGLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);
        CreateShapeCommand createShapeCommand2 = new CreateShapeCommand(type2, map2);
        CreateShapeCommand createShapeCommand3 = new CreateShapeCommand(type3, map3);
        CreateShapeCommand createShapeCommand4 = new CreateShapeCommand(type, map4);

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);
        String json5 = gson.toJson(createShapeCommand2);
        String json6 = gson.toJson(createShapeCommand3);
        String json7 = gson.toJson(createShapeCommand4);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());
        Map<String, String> savedToken2 = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token2 = savedToken2.get("token");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(2.0))
                .andExpect(jsonPath("$.area").value(12.56))
                .andExpect(jsonPath("$.perimeter").value(12.56))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json5)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.side").value(3.0))
                .andExpect(jsonPath("$.area").value(9.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json6)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.width").value(4.0))
                .andExpect(jsonPath("$.height").value(5.0))
                .andExpect(jsonPath("$.area").value(20.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json7)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(7.0))
                .andExpect(jsonPath("$.area").value(153.86))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8000/api/v1/shapes?createdBy=test@gmail.com&areaFrom=19.0&areaTo=30.0&widthFrom=3.0&heightFrom=4.0")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(3))
                .andExpect(jsonPath("$.[0].createdBy").value("test@gmail.com"))
                .andExpect(jsonPath("$.size()").value(1));

    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void shouldFilterShapes_CIRCLE() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        Map<String, Double> map2 = new HashMap<>();
        Map<String, Double> map3 = new HashMap<>();
        Map<String, Double> map4 = new HashMap<>();
        map.put("radius", 2.0);
        map2.put("side", 3.0);
        map3.put("width", 4.0);
        map3.put("height", 5.0);
        map4.put("radius", 7.0);
        String type = "CIRCLE";
        String type2 = "SQUARE";
        String type3 = "RECTANGLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);
        CreateShapeCommand createShapeCommand2 = new CreateShapeCommand(type2, map2);
        CreateShapeCommand createShapeCommand3 = new CreateShapeCommand(type3, map3);
        CreateShapeCommand createShapeCommand4 = new CreateShapeCommand(type, map4);

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);
        String json5 = gson.toJson(createShapeCommand2);
        String json6 = gson.toJson(createShapeCommand3);
        String json7 = gson.toJson(createShapeCommand4);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());
        Map<String, String> savedToken2 = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token2 = savedToken2.get("token");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(2.0))
                .andExpect(jsonPath("$.area").value(12.56))
                .andExpect(jsonPath("$.perimeter").value(12.56))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json5)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.side").value(3.0))
                .andExpect(jsonPath("$.area").value(9.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json6)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.width").value(4.0))
                .andExpect(jsonPath("$.height").value(5.0))
                .andExpect(jsonPath("$.area").value(20.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json7)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(7.0))
                .andExpect(jsonPath("$.area").value(153.86))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8000/api/v1/shapes?createdBy=test@gmail.com&version=0&radiusFrom=6.0&radiusTo=10.0")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(4))
                .andExpect(jsonPath("$.[0].createdBy").value("test@gmail.com"))
                .andExpect(jsonPath("$.size()").value(1));
    }
    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void shouldFilterShapes_SQUARE() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        Map<String, Double> map2 = new HashMap<>();
        Map<String, Double> map3 = new HashMap<>();
        Map<String, Double> map4 = new HashMap<>();
        map.put("radius", 2.0);
        map2.put("side", 3.0);
        map3.put("width", 4.0);
        map3.put("height", 5.0);
        map4.put("radius", 7.0);
        String type = "CIRCLE";
        String type2 = "SQUARE";
        String type3 = "RECTANGLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);
        CreateShapeCommand createShapeCommand2 = new CreateShapeCommand(type2, map2);
        CreateShapeCommand createShapeCommand3 = new CreateShapeCommand(type3, map3);
        CreateShapeCommand createShapeCommand4 = new CreateShapeCommand(type, map4);

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);
        String json5 = gson.toJson(createShapeCommand2);
        String json6 = gson.toJson(createShapeCommand3);
        String json7 = gson.toJson(createShapeCommand4);


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/register")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        Map<String, String> savedToken = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token = savedToken.get("token");

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json2)))
                .andExpect(status().isOk());
        Map<String, String> savedToken2 = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/users/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(json3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), Map.class);

        String token2 = savedToken2.get("token");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(2.0))
                .andExpect(jsonPath("$.area").value(12.56))
                .andExpect(jsonPath("$.perimeter").value(12.56))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json5)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.side").value(3.0))
                .andExpect(jsonPath("$.area").value(9.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json6)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.width").value(4.0))
                .andExpect(jsonPath("$.height").value(5.0))
                .andExpect(jsonPath("$.area").value(20.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2)
                        .content(String.valueOf(json7)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(7.0))
                .andExpect(jsonPath("$.area").value(153.86))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8000/api/v1/shapes?createdBy=test@gmail.com&version=0&sideFrom=2.0&sideTo=6.0")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(2))
                .andExpect(jsonPath("$.[0].createdBy").value("test@gmail.com"))
                .andExpect(jsonPath("$.size()").value(1));
    }
}
