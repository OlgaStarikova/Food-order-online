package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.UserRegistrationRequestDto;
import com.service.foodorderonline.dto.UserResponseDto;
import com.service.foodorderonline.exception.RegistrationException;
import com.service.foodorderonline.mapper.UserMapper;
import com.service.foodorderonline.model.Role;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.repository.role.RoleRepository;
import com.service.foodorderonline.repository.user.UserRepository;
import com.service.foodorderonline.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("The user with email "
                    + requestDto.email()
                    + " is already registered");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setRoles(Set.of(roleRepository.getByRole(Role.RoleName.USER)));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
