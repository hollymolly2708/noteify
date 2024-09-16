package com.noteify.note;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noteify.note.model.WebResponse;
import com.noteify.note.model.user.request.RegisterUserRequest;
import com.noteify.note.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("fiqriturhamz");
        request.setPassword("rahasia");
        request.setEmail("tmuhammadfiqri@gmail.com");
        request.setPhone("08232323");
        request.setFullName("Muhammad Fiqri Turham");
        request.setAddress("Taman Ciruas Permai Blok e5 no 6");
        mockMvc.perform(post("/noteify/auth/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk()).andDo(result -> {
            WebResponse<String> stringWebResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            Assertions.assertEquals("User berhasil dibuat", stringWebResponse.getData());
        });
    }
}
