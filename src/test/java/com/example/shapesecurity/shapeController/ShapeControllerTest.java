package com.example.shapesecurity.shapeController;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.user.Role;
import com.example.shapesecurity.model.user.User;
import com.example.shapesecurity.repository.UserRepository;
import com.example.shapesecurity.service.impl.JwtServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.HashMap;
import java.util.Map;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.yml")
public class ShapeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private UserRepository userRepository;

    private String generateTokenWithRole(Role role) {
        User user = new User(1, "test", "user", "test@user.com","password",null);
        user.setFirstname("test");
        user.setLastname("user");
        user.setEmail("test@user.com");
        user.setPassword("password");
        user.setRole(role);
        String token = jwtService.generateToken(user);
        return "Bearer " + token;
    }

//    @Test
//    @PrepareForTest(SecurityContextHolder.class)
//    public void testShouldSaveCircle() throws Exception {
//
//        Map<String, Double> map = new HashMap<>();
//        map.put("radius", 2.0);
//        String type = "CIRCLE";
//        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(createShapeCommand);
//        String token = generateTokenWithRole(Role.ROLE_CREATOR);
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
//                        .contentType(APPLICATION_JSON)
//                        .header("Authorization", token)
//                        .content(String.valueOf(json)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(jsonPath("$.radius").value(2.0))
//                .andExpect(jsonPath("$.area").value(12.56))
//                .andExpect(jsonPath("$.perimeter").value(12.56));
//    }

    @Test
    public void whenValidInputAndCreatorRole_thenReturns201() throws Exception {
        // given
        CreateShapeCommand createRequest = new CreateShapeCommand();
        createRequest.setType("rectangle");
        Map<String, Double> params = new HashMap<>();
        params.put("width", 10.0);
        params.put("height", 20.0);
        createRequest.setParameters(params);

        String token = generateTokenWithRole(Role.ROLE_CREATOR);

        // when
        mvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(createRequest)))
                // then
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.type", is(createRequest.getType())))
                .andExpect((ResultMatcher) jsonPath("$.parameters.width", is(createRequest.getParameters().get("width").doubleValue())))
                .andExpect((ResultMatcher) jsonPath("$.parameters.height", is(createRequest.getParameters().get("height").doubleValue())));
    }

    @Test
    public void whenValidInputAndWrongRole_thenReturns403() throws Exception {
        // given
        CreateShapeCommand createRequest = new CreateShapeCommand();
        createRequest.setType("circle");
        Map<String, Double> params = new HashMap<>();
        params.put("radius", 10.0);
        createRequest.setParameters(params);

        String token = generateTokenWithRole(Role.ROLE_USER);

        // when
        mvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(createRequest)))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenValidInputAndNoToken_thenReturns401() throws Exception {
        // given
        CreateShapeCommand createRequest = new CreateShapeCommand();
        createRequest.setType("triangle");
        Map<String, Double> params = new HashMap<>();
        params.put("a", 10.0);
        params.put("b", 15.0);
        params.put("c", 12.0);
        createRequest.setParameters(params);

        // when
           Gson gson = new Gson();
                String json = gson.toJson(createRequest);
        mvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(json)))
                // then
                .andExpect(status().isUnauthorized());
    }
    //   Gson gson = new Gson();
    //        String json = gson.toJson(createShapeCommand);
    //
    //        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
    //                        .contentType(APPLICATION_JSON)
    //                        .content(String.valueOf(json)))
}
