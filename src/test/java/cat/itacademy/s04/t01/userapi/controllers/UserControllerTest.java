package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUsers_returnsEmptyListInitially() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void createUser_returnsUserWithId() throws Exception {
        UUID id = UUID.randomUUID();
        User userRequest = new User(id, "Pau", "pau@pau.com");
        String userJason = objectMapper.writeValueAsString(userRequest);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(userJason))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());

    }

    @Test
    void getUserById_returnsCorrectUser() throws Exception {

        User userRequest = new User(null, "Pau", "pau@pau.com");
        String userJason = objectMapper.writeValueAsString(userRequest);

        MvcResult result = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(userJason))
                .andExpect(status().isOk())
                .andReturn();
        // Primer afegeix un usuari amb POST

        String responseBody = result.getResponse().getContentAsString();
        User createdUser = objectMapper.readValue(responseBody,User.class);

        mockMvc.perform(get("/users/{id}", createdUser.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUser.getId().toString()));
        // Després GET /users/{id} i comprova que torni aquest usuari
    }

    @Test
    void getUserById_returnsNotFoundIfMissing() {
        // Simula GET /users/{id} amb un id aleatori
        // Espera 404
    }

    @Test
    void getUsers_withNameParam_returnsFilteredUsers() {
        // Afegeix dos usuaris amb POST
        // Fa GET /users?name=jo i comprova que només torni els que contenen "jo"
    }

}