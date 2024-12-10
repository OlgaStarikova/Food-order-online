package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.UserLoginResponseDto;
import com.service.foodorderonline.dto.UserRegistrationRequestDto;

public interface UserService {
    UserLoginResponseDto register(UserRegistrationRequestDto requestDto);
}
