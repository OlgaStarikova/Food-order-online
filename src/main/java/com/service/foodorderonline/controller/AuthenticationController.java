package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.UserLoginRequestDto;
import com.service.foodorderonline.dto.UserLoginResponseDto;
import com.service.foodorderonline.dto.UserRegistrationRequestDto;
import com.service.foodorderonline.exception.RegistrationException;
import com.service.foodorderonline.security.AuthenticationService;
import com.service.foodorderonline.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User management", description = "Endpoints for managing users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Registration "
            + " a user for a access to data")
    @PostMapping("/register")
    public UserLoginResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @Operation(summary = "Login a user", description = "Authentication and login "
            + " a user for a access to data")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
