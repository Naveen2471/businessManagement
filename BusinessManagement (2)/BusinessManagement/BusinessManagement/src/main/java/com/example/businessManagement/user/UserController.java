package com.example.businessManagement.user;

import com.example.businessManagement.Common.ApiResponse;
import com.example.businessManagement.Model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest req)
    {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setRole(Role.valueOf(req.getRole().name()));


        return new ApiResponse<>(true, "User created",
                mapper.toResponse(service.create(user)));
    }
}
