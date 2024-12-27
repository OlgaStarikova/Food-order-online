package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.UserProfileResponseDto;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User profile management", description = "Endpoints for user's profile")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class ProfileController {
    private final UserService userService;

    @Operation(summary = "Get a user's profile", description = "Get a user's profile "
            + "for authentication user ")
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('USER')")
    public UserProfileResponseDto profile(Authentication authentication,
                                          @ParameterObject @PageableDefault Pageable pageable
    ) {
        User user = (User) authentication.getPrincipal();
        return userService.profile(user, pageable);
    }
}
