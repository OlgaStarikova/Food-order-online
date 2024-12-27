package com.service.foodorderonline.service.impl;

import com.service.foodorderonline.dto.OrderCurrentDto;
import com.service.foodorderonline.dto.OrderDto;
import com.service.foodorderonline.dto.UserLoginRequestDto;
import com.service.foodorderonline.dto.UserLoginResponseDto;
import com.service.foodorderonline.dto.UserProfileResponseDto;
import com.service.foodorderonline.dto.UserRegistrationRequestDto;
import com.service.foodorderonline.exception.RegistrationException;
import com.service.foodorderonline.mapper.OrderMapper;
import com.service.foodorderonline.mapper.UserMapper;
import com.service.foodorderonline.model.Order;
import com.service.foodorderonline.model.Role;
import com.service.foodorderonline.model.User;
import com.service.foodorderonline.repository.order.OrderRepository;
import com.service.foodorderonline.repository.role.RoleRepository;
import com.service.foodorderonline.repository.user.UserRepository;
import com.service.foodorderonline.security.AuthenticationService;
import com.service.foodorderonline.service.UserService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final OrderMapper orderMapper;

    @Override
    public UserLoginResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("The user with email "
                    + requestDto.email()
                    + " is already registered");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setRoles(Set.of(roleRepository.getByRole(Role.RoleName.USER)));
        User savedUser = userRepository.save(user);
        UserLoginRequestDto loginRequestDto = new UserLoginRequestDto(
                requestDto.email(), requestDto.password());
        return authenticationService.authenticate(loginRequestDto);
    }

    @Override
    public UserProfileResponseDto profile(User user, Pageable pageable) {
        List<Order> ordersList = orderRepository
                .findOrdersByUserId(user.getId(), pageable);

        List<OrderDto> orderDtos = orderMapper.toOrdersDto(ordersList);

        List<OrderCurrentDto> orderCurrentDtos = orderMapper.toOrderCurrentsDto(
                ordersList.stream()
                        .filter(o -> o.getStatus() == Order.Status.PENDING)
                        .toList()
        );
        return userMapper.toProfileDto(user)
                .setOrderCurrentList(orderCurrentDtos)
                .setOrderList(orderDtos);
    }
}
