package az.edu.turing.fintechproproject;
import az.edu.turing.fintechproproject.controller.UserController;
import az.edu.turing.fintechproproject.model.dto.UserDto;
import az.edu.turing.fintechproproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@SpringBootTest
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testFindAll() throws Exception {
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        when(userService.findAll()).thenReturn(Arrays.asList(userDto1, userDto2));

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFindById() throws Exception {
        UserDto userDto = new UserDto();

        when(userService.findById(anyLong())).thenReturn(Optional.of(userDto));

        mockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testCreate() throws Exception {
        UserDto userDto = new UserDto();
        UserDto createdUserDto = new UserDto();

        when(userService.create((UserDto) any(UserDto.class))).thenReturn(createdUserDto);

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testUpdate() throws Exception {
        UserDto userDto = new UserDto();
        UserDto updatedUserDto = new UserDto();

        when(userService.update(anyLong(), (UserDto) any(UserDto.class))).thenReturn(updatedUserDto);

        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testUpdateUserPassword() throws Exception {
        UserDto updatedUserDto = new UserDto();

        when(userService.updatePassword(anyLong(), anyString())).thenReturn(Optional.of(updatedUserDto));

        mockMvc.perform(patch("/api/users/{id}/password", 1L)
                        .param("password", "newPassword"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUserById() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAllUsers() throws Exception {
        mockMvc.perform(delete("/api/users/"))
                .andExpect(status().isNoContent());
    }
}