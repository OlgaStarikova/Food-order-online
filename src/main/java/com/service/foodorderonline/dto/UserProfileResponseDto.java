package com.service.foodorderonline.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserProfileResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String shippingAddress;
    private List<OrderCurrentDto> orderCurrentList;
    private List<OrderDto> orderList;
}
