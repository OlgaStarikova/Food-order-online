package com.service.foodorderonline.mapper;

import com.service.foodorderonline.config.MapperConfig;
import com.service.foodorderonline.dto.UserProfileResponseDto;
import com.service.foodorderonline.dto.UserRegistrationRequestDto;
import com.service.foodorderonline.dto.UserResponseDto;
import com.service.foodorderonline.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    UserProfileResponseDto toProfileDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
