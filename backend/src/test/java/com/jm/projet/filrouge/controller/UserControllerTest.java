package com.jm.projet.filrouge.controller;


import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserController restController;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup (restController)
                .build ( );
    }

    @Test
    void whenFindAll_thenReturnUserList() throws Exception {
        UserDTO userDTO = UserDTO.builder ( )
                .id (1L)
                .firstname ("santa")
                .build ( );

        List<UserDTO> expectedUsers = Arrays.asList (userDTO);

        given (userService.findAll ( )).willReturn (expectedUsers);

        mockMvc.perform (get ("/api/users")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect (status ( ).isOk ( ))
                .andExpect (content ( )
                        .contentTypeCompatibleWith (MediaType.APPLICATION_JSON))
                .andExpect (jsonPath ("$[0].firstName", is ("santa")));
    }

    @Test
    void whenFindById_thenReturnUser() throws Exception {
        UserDTO userDTO = UserDTO.builder ( )
                .id (1L)
                .firstname ("santa")
                .build ( );

        given (userService.findById (1L)).willReturn (userDTO);

        mockMvc.perform (get ("/api/users/1")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect (status ( ).isOk ( ))
                .andExpect (content ( )
                        .contentTypeCompatibleWith (MediaType.APPLICATION_JSON))
                .andExpect (jsonPath ("firstName", is ("santa")));
    }

    @Test
    void whenFindUserByLogin_thenReturnUser() throws Exception {
        UserDTO userDTO = UserDTO.builder ( )
                .id (1L)
                .firstname ("santa")
                .build ( );

        given (userService.findUserByLogin ("login")).willReturn (userDTO);

        mockMvc.perform (get ("/api/users/login?user=login")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect (status ( ).isOk ( ))
                .andExpect (content ( )
                        .contentTypeCompatibleWith (MediaType.APPLICATION_JSON))
                .andExpect (jsonPath ("firstName", is ("santa")));

    }

    @Test
    public void should_successfully_save_user() throws Exception {
        UserDTO userDTO = UserDTO.builder ( )
                .id (2L)
                .firstname ("new person")
                .build ( );

        given (userService.save (userDTO)).willReturn (userDTO);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 2, \"firstName\": \"new person\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "/api/users/2"))
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.firstName").value("new person"));
    }
}
