package com.service.foodorderonline.service;

import com.service.foodorderonline.dto.UserLoginResponseDto;
import com.service.foodorderonline.dto.UserProfileResponseDto;
import com.service.foodorderonline.dto.UserRegistrationRequestDto;
import com.service.foodorderonline.model.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserLoginResponseDto register(UserRegistrationRequestDto requestDto);

    UserProfileResponseDto profile(User user, Pageable pageable);
}
