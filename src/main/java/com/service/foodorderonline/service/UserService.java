package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.UserRegistrationRequestDto;
import com.service.foodorderonline.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
