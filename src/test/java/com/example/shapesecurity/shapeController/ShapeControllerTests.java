package com.example.shapesecurity.shapeController;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

    // wyslac token z zapytaniem po token, wyciagnac przez objectMappera tokena, response z deserializacja do mapy po kluczu
    // beforeEach
    // potem token w mvc perform
// mock mvc authorization
    private Gson gsonFilter = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    //    private FilterRequest createFilterRequest() {
//        return new FilterRequest("test@gmail.com", null, 100.0, 150.0, null, null,
//                LocalDateTime.parse("2022-10-10T00:00:00"), LocalDateTime.parse("2023-10-10T00:00:00"),
//                1L, null, null, null, null, null, null, 1.0, 10.0);
//    }
    private FilterRequest createFilterRequest() {
        return new FilterRequest("test@gmail.com", null, 100.0, 150.0, null, null,
                null, null,
                null, null, null, 3.0, 4.0, 4.0, 5.0, null, null);
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
    public void shouldUpdateCircle() throws Exception {
        CreateUserCommand createUserCommand = new CreateUserCommand("Test", "Test", "test@gmail.com", "Test");
        Permission permission = new Permission(createUserCommand.getEmail(), "CREATOR");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(createUserCommand.getEmail(), createUserCommand.getPassword());
        Map<String, Double> map = new HashMap<>();
        map.put("radius", 2.0);
        String type = "CIRCLE";
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(type, map);

        Map<String, Double> map2 = new HashMap<>();
        map2.put("radius", 8.0);
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, map2);

        Gson gson = new Gson();
        String json = gson.toJson(createUserCommand);
        String json2 = gson.toJson(permission);
        String json3 = gson.toJson(authenticationRequest);
        String json4 = gson.toJson(createShapeCommand);
        String json5 = gson.toJson(updateShapeCommand);


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

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json5)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(8.0))
                .andExpect(jsonPath("$.area").value(200.96))
                .andExpect(jsonPath("$.perimeter").value(50.24))
                .andExpect(jsonPath("$.lastModifiedBy").value("test@gmail.com"))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));
    }

    @Test
    @PrepareForTest(SecurityContextHolder.class)
    public void shouldFilterShapes() throws Exception {
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

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json5)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.side").value(3.0))
                .andExpect(jsonPath("$.area").value(9.0))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
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
                        .header("Authorization", "Bearer " + token)
                        .content(String.valueOf(json7)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.radius").value(7.0))
                .andExpect(jsonPath("$.area").value(153.86))
                .andExpect(jsonPath("$.createdBy").value("test@gmail.com"));
//
//        String json22 = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(new FilterRequest("test@gmail.com", null, 100.0, 150.0, null, null,
//                null, null,
//                null, null, null, 3.0, 4.0, 4.0, 5.0, null, null));
//        FilterRequest jsonFiltr = objectMapper.readValue(json22, FilterRequest.class);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)));
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)));
//        objectMapper.registerModule(javaTimeModule);
//
////        String json997 = "{\"shapeType\":\"RECTANGLE\",\"areaFrom\":10.0,\"areaTo\":20.0,\"perimeterFrom\":12.0,\"perimeterTo\":22.0,\"createdAtFrom\":\"2023-02-15T16:30:00.000\",\"createdAtTo\":\"2023-02-18T16:30:00.000\",\"version\":1,\"sideFrom\":5.0,\"sideTo\":10.0,\"widthFrom\":2.0,\"widthTo\":4.0,\"heightFrom\":3.0,\"heightTo\":6.0,\"radiusFrom\":1.0,\"radiusTo\":3.0}";
//
////        FilterRequest filterRequest = objectMapper.readValue(json997, FilterRequest.class);
//        Gson gsonTest = new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                .create();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME);
        javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME);
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
        objectMapper.registerModule(javaTimeModule);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);




        FilterRequest filterRequest = new FilterRequest("test@gmail.com", null, 100.0, 150.0, null, null, null, null, null, null, null, 3.0, 4.0, 4.0, 5.0, null, null);
        String jsontest = objectMapper.writeValueAsString(filterRequest);//        String jsonDzialajPlis = gsonTest.toJson(filterRequest);
//        System.out.println(jsonDzialajPlis + " TO JEST JSONNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        List<Shape> savedList = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8000/api/v1/shapes")
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(jsontest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn().getResponse().getContentAsByteArray(), List.class);
//                .andExpect(jsonPath("$.[0].radius").value(7.0))
//                .andExpect(jsonPath("$.[0].area").value(153.86))
//                .andExpect(jsonPath("$.[0].createdBy").value("test@gmail.com"))
//                .andExpect(jsonPath("$.[3].id").value(4));
        System.out.println(savedList);
    }
    //ObjectMapper objectMapper = new ObjectMapper();
    //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //JavaTimeModule javaTimeModule = new JavaTimeModule();
    //javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)));
    //javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)));
    //objectMapper.registerModule(javaTimeModule);
    //
    //String json = "{\"createdBy\":\"User1\",\"shapeType\":\"square\",\"areaFrom\":10.0,\"areaTo\":20.0,\"perimeterFrom\":12.0,\"perimeterTo\":22.0,\"createdAtFrom\":\"2023-02-15T16:30:00.000\",\"createdAtTo\":\"2023-02-18T16:30:00.000\",\"version\":1,\"sideFrom\":5.0,\"sideTo\":10.0,\"widthFrom\":2.0,\"widthTo\":4.0,\"heightFrom\":3.0,\"heightTo\":6.0,\"radiusFrom\":1.0,\"radiusTo\":3.0}";
    //
    //FilterRequest filterRequest = objectMapper.readValue(json, FilterRequest.class);
}
