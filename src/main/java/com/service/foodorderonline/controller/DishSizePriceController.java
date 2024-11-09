package com.service.foodorderonline.controller;

import com.service.foodorderonline.dto.CreateDishSizePriceRequestDto;
import com.service.foodorderonline.dto.DishSizePriceDto;
import com.service.foodorderonline.service.DishSizePriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "DishSizePrice management", description = "Endpoints for managing "
        + "dish sizes and prices")
@RequestMapping("/dishes/sizes")
@RequiredArgsConstructor
public class DishSizePriceController {
    private final DishSizePriceService dishSizePriceService;

    @PostMapping
    @Operation(summary = "Create a new dishSizePriceient",
            description = "Create a new dishSizePriceient. "
                    + "Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DishSizePriceDto createDishSizePrice(@RequestBody @Valid
                                                CreateDishSizePriceRequestDto requestDto) {
        return dishSizePriceService.save(requestDto);
    }

    @GetMapping("/{dishId}")
    @Operation(summary = "Find the dishSizePrice by dishId",
            description = "Find the dishSizePrice by dishId"
                    + "Params: id = Id of the dishSizePrice. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<DishSizePriceDto> getDishSizePriceById(@PathVariable Long dishId) {
        return dishSizePriceService.findsByDishId(dishId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the dishSizePrice",
            description = "Delete the dishSizePrice by Id."
                    + "Params: id = Id of the dishSizePrice. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDishSizePrice(@PathVariable Long id) {
        dishSizePriceService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the dishSizePrice", description = "Update the dishSizePrice by Id"
            + "Params: id = Id of the dishSizePriceient. Available for admins.")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DishSizePriceDto updateDishSizePrice(@PathVariable Long id,
                                                @RequestBody @Valid CreateDishSizePriceRequestDto
                                                        requestDto) {
        return dishSizePriceService.update(id, requestDto);
    }
}
