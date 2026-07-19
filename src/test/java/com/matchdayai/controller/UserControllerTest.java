package com.matchdayai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchdayai.dto.UserRequest;
import com.matchdayai.dto.UserResponse;
import com.matchdayai.enums.Role;
import com.matchdayai.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createUser_ValidRequest_ReturnsSuccess() throws Exception {
        UserRequest request = new UserRequest();
        request.setName("John Doe");
        request.setEmail("john@example.com");
        request.setPassword("password123");
        request.setRole("ROLE_VISITOR");

        UserResponse response = UserResponse.builder()
                .id("1")
                .name("John Doe")
                .email("john@example.com")
                .role(Role.ROLE_VISITOR)
                .build();

        Mockito.when(userService.createUser(any(UserRequest.class))).thenReturn(response);

        com.matchdayai.dto.ApiResponse<UserResponse> apiResponse = userController.createUser(request);
        
        assertEquals("John Doe", apiResponse.getData().getName());
    }

    @Test
    void getAllUsers_ReturnsList() throws Exception {
        UserResponse user = UserResponse.builder()
                .id("1")
                .name("John Doe")
                .build();

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user));

        List<UserResponse> list = userController.getAllUsers();
        assertEquals(1, list.size());
        assertEquals("John Doe", list.get(0).getName());
    }
}
